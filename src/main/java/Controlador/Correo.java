package Controlador;

import com.resend.*;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;

public class Correo {
    private final Resend resend;

    public Correo(String apiKey) {
        resend = new Resend(apiKey);
    }

    public void enviarCorreo(String destinatario, String asunto, String contenidoHtml) {
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from("Objective Class <mensajeria@objetiveclass.top>")
                .to(destinatario)
                .subject(asunto)
                .html(contenidoHtml)
                .build();

        try {
            resend.emails().send(params);
            System.out.println("Correo enviado correctamente a " + destinatario);
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
