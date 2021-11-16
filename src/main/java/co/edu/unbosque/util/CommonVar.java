package co.edu.unbosque.util;

import java.util.Base64;

public class CommonVar {

	public static final String SALT = Base64.getEncoder()
			.encodeToString("!6(*^<>4?}L(&HGsd2asd*&@3*f4h&^6D$@#gfd54DSS!113af524f3%$%F#VSDT^".getBytes());

	public static final long DATE_TOKEN_EXPIRATION = 14000000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";

}
