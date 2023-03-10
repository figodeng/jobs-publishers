package figo.external.jobs.core.services;

import figo.external.jobs.core.models.Job;
import figo.external.jobs.core.models.Recruitment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class MarkdownService implements SubscribeService {
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
    private final Set<String> allFiles;
    private final static String Folder = "jobs";

    public MarkdownService() {
        allFiles = Stream.of(new File(Folder).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
        writeReadME(allFiles);
    }

    @Override
    public void subscribe(Recruitment recruitment) {
        String fileName = String.format("%s.md", recruitment.getPublisher());
        String filePath = String.format("%s/%s", Folder, fileName);
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("# jobs-publishers \n");
            writer.write(getLinks(recruitment.getPublisher()));
            int i = 0;
            for (Job job : recruitment.getJobs()) {
                writer.write(String.format(">%s. %s,%s,%s \\ \n", ++i, job.getTitle(), format.format(job.getPublishDate()), job.getUrl()));
            }
        } catch (Exception e) {
            log.error("markdown error", e);
        }
    }

    private String getLinks(String cur) {
        Iterator<String> iterator = allFiles.iterator();
        if (!iterator.hasNext()) return "";

        String next = iterator.next();
        StringBuilder builder = new StringBuilder();
        while (iterator.hasNext()) {
            builder.append(next.contains(cur) ? cur : String.format("[%s](%s)", next.replace(".md", ""), next));
            builder.append(" | ");
            next = iterator.next();
        }
        builder.append(next.contains(cur) ? cur : String.format("[%s](%s)", next.replace(".md", ""), next));
        builder.append("\n");
        return builder.toString();
    }

    private void writeReadME(Set<String> allFiles) {
        try (FileWriter writer = new FileWriter("README.md")) {
            writer.write("# jobs-publishers \n");
            List<String> files = allFiles.stream().map(p -> String.format("[%s](jobs/%s)", p.replace(".md", ""), p)).collect(Collectors.toList());
            writer.write(String.join(" | ", files));
        } catch (Exception e) {
            log.error("markdown error", e);
        }
    }
}
