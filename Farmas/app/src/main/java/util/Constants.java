package util;

/**
 * Created by Romano on 11/09/2017.
 */

public class Constants {

    /**
     * URI da api que roda na porta 3001, construída para consumir os dados localização da empresa FARMA ONLINE
     */
    public static final String URI_CONSULTAR_DADOS_FARMA_ONLINE = "http://10.0.2.2:3001/farmaonline/contato";


    /**
     * URIs utilizadas para consumir serviços referentes aos dados dos produtos e das empresas que os vende.
     */
    public static final String URI_BASE = "http://10.0.2.2:3000";
    public static final String URI_IMAGES = URI_BASE + "/public/images/";
    public static final String URI_ALL_PRODUCTS = URI_BASE + "/produto";
    public static final String URI_LOGIN = URI_BASE + "/usuario/logar";
    public static final String URI_POST_USER = URI_BASE + "/usuario";
    public static final String URI_POST_PEDIDO = URI_BASE + "/pedido";


    /**
     * Constante utilizadas para troca de parâmetros com o fragment do carrinho de compras
     */
    public static final String DESCONTO = "DESSCONTO";
    public static final String SUB_TOTAL = "SUB_TOTAL";
    public static final String TOTAL = "TOTAL";
    public static final String QTD_ITENS = "QTD_ITENS";
    public static final String SHOW_BUTTON = "SHOW_BUTTON";

    /**
     * Request
     */
    public static final String APPLICATION_JSON = "application/json";

    public static final int HTTP_SUCCESS = 200;
    public static final int HTTP_CREATED = 201;
    public static final int HTTP_UNAUTHORIZED = 401;
}
