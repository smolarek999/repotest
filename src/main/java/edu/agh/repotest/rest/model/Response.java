/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.agh.repotest.rest.model;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author pawel
 */
public abstract class Response {

    public static final String BASE_URI = "http://localhost:8080/repoTest/rest/";
    public static final String NAMESPACE =
            "http://schemas.testrepo.agh,edu.pl";
    public static final String MEDIA_TYPE =
            "application/vnd.testrepo+xml";
    protected static String cookUrl( String rawUrl, Map<String, String> ingredients ){
        Pattern placeholdersPattern = Pattern.compile("\\$\\{(.*?)\\}");
        final Matcher matcher = placeholdersPattern.matcher(rawUrl);
        StringBuffer sb = new StringBuffer();
        while( matcher.find()){
            String keyName = matcher.group(1);
            if( ingredients.containsKey(keyName)){
                matcher.appendReplacement(sb, ingredients.get(keyName));
            }else{
                throw new IllegalArgumentException("No key found " + keyName);
            }
            
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
    
    @XmlElement(name = "link", namespace = NAMESPACE)
    protected List<Link> links;
}
