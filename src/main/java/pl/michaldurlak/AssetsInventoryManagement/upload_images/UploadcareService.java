package pl.michaldurlak.AssetsInventoryManagement.upload_images;

import com.uploadcare.api.Client;
import com.uploadcare.api.File;
import com.uploadcare.api.Project;
import lombok.Getter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
public class UploadcareService {

    public UploadcareService() {
    }

    private final static String API_UPLOADCARE_PUBLIC = System.getenv("API_UPLOADCARE_PUBLIC");
    private final static String API_UPLOADCARE_PRIVATE = System.getenv("API_UPLOADCARE_PRIVATE");

    // CREATE CONNECTION TO UPLOADCARE
    private Client client = new Client(API_UPLOADCARE_PUBLIC, API_UPLOADCARE_PRIVATE);

//    private Project project = client.getProject();
//    private Project.Collaborator owner = project.getOwner();
//    private List<URI> published = new ArrayList<URI>();


    @EventListener(ApplicationReadyEvent.class)
    private void ListOfAllUploadedImages(){
        Iterable<File> files = client.getFiles().asIterable();
        for (File file : files) {
            System.out.println("https://ucarecdn.com/" + file.getFileId() +"/");
        }
    }




}
