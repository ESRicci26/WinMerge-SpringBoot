package com.javaricci.WinMerge.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FileController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/analyze")
    public String analyzeFiles(@RequestParam("file1") MultipartFile file1,
                               @RequestParam("file2") MultipartFile file2,
                               Model model) {
        try {
            String content1 = new String(file1.getBytes(), StandardCharsets.UTF_8);
            String content2 = new String(file2.getBytes(), StandardCharsets.UTF_8);

            List<String> lines1 = Arrays.asList(content1.split("\\r?\\n"));
            List<String> lines2 = Arrays.asList(content2.split("\\r?\\n"));

            List<String> html1 = new ArrayList<>();
            List<String> html2 = new ArrayList<>();

            int max = Math.max(lines1.size(), lines2.size());
            for (int i = 0; i < max; i++) {
                String line1 = i < lines1.size() ? lines1.get(i) : "";
                String line2 = i < lines2.size() ? lines2.get(i) : "";

                if (!line1.equals(line2)) {
                    html1.add(highlightDifferences(line1, line2));
                    html2.add(highlightDifferences(line2, line1));
                } else {
                    html1.add(escapeHtml(line1));
                    html2.add(escapeHtml(line2));
                }
            }

            model.addAttribute("file1Content", String.join("<br/>", html1));
            model.addAttribute("file2Content", String.join("<br/>", html2));

        } catch (Exception e) {
            model.addAttribute("error", "Erro ao processar arquivos: " + e.getMessage());
        }

        return "analyze";
    }

    private String highlightDifferences(String base, String compare) {
        if (base == null) base = "";
        if (compare == null) compare = "";

        String[] baseWords = base.split("(?<=\\s)|(?=\\s)");
        String[] compWords = compare.split("(?<=\\s)|(?=\\s)");
        int max = Math.max(baseWords.length, compWords.length);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < max; i++) {
            String w1 = i < baseWords.length ? baseWords[i] : "";
            String w2 = i < compWords.length ? compWords[i] : "";
            if (!w1.equals(w2)) {
                sb.append("<span class='diff'>").append(escapeHtml(w1)).append("</span>");
            } else {
                sb.append(escapeHtml(w1));
            }
        }
        return sb.toString();
    }

    private String escapeHtml(String s) {
        if (s == null) return "";
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;");
    }
}
