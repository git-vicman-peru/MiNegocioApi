package admin.api.general;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service("emailerApi")
public class EMailerApi {

	@Autowired
	private MailSender cmail;
	
	public void ReadyToSendEmail(String toAddress, String fromAddress, String subject, String msgBody){
		SimpleMailMessage smsg = new SimpleMailMessage();
		smsg.setFrom(fromAddress);
		smsg.setTo(toAddress);
		smsg.setSubject(subject);
		smsg.setText(msgBody);
		cmail.send(smsg);
	}
}
