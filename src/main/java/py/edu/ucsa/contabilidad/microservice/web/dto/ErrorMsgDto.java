package py.edu.ucsa.contabilidad.microservice.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMsgDto {
	private int status;
    private String message;
    private String detail;
    
}
