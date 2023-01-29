package com.bb.focus.api.service;

import java.util.Map;
import javax.mail.MessagingException;

public interface MailService {

  //public void sendGeneralMail(MailDto mail);

  public void sendAccountMail(String to, Map<String, String> content) throws MessagingException;

}
