package br.com.drogasil.exception;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.drogasil.exception.Problem.Campo;

@ControllerAdvice
public class DrogasilExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<Object> handleNegocio(BusinessException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitulo(ex.getMessage());
		problem.setDataHora(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ArrayList<Campo> campos = new ArrayList<Problem.Campo>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new Problem.Campo(nome, mensagem));
		}
		
		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitulo("Um ou mais campos estão inválidos. "
				+ "Faça o preenchimento correto e tente novamente");
		problem.setDataHora(OffsetDateTime.now());
		problem.setCampos(campos);
		
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}	
		
	@Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

		Problem problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitulo("Informações Inválidas! " +
		                  "Informe apenas Números, nos campos de Valores.");
		problem.setDataHora(OffsetDateTime.now());

		
        return handleExceptionInternal(ex, problem, headers, status, request);
    }		
}
