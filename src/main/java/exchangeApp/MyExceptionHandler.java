package exchangeApp;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
@Log4j2
public class MyExceptionHandler {

    private static final Marker BAD_REQUEST_MARKER= MarkerManager.getMarker("BAD_REQUEST");
    private static final Marker EXCEPTION_MARKER= MarkerManager.getMarker("EXCEPTION");

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<String> handle(IllegalArgumentException ex) {
        log.warn(BAD_REQUEST_MARKER,ex.getMessage());
        return new ResponseEntity<>(ex.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<String> handleE(RuntimeException ex) {
        log.error(EXCEPTION_MARKER,beautifyStackTrace(ex));
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private String beautifyStackTrace(Throwable ex){
        StringWriter stringWriter=new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return ex.getMessage()+'\n'+stringWriter.toString();

    }
}
