package mrDetailer.mrDetailer.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MyObjectDto {
    private Long id;
    private String text;
    private List<MultipartFile> photos;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate localDate = LocalDate.now();
}
