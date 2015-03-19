package part1;
import java.util.*;
import org.json.*;

/**
 * Created by ruiboli on 3/18/15.
 */
public class Extractor {
    public List<String> getEntity(JSONObject jsonObject){
        return null;
    }

    public Map<String,List<String>> getAttribute(JSONObject jsonObject){
        return null;
    }

    private static void getHelper(Map<String, List<String>>  map, JSONObject jsonObject, String path, String title){
        if(jsonObject.has(path)) {
            JSONObject Object  = jsonObject.getJSONObject(path);
            if(Object.has("values")) {
                JSONArray values = Object.getJSONArray("values");
                List <String> list = new LinkedList <String> ();
                for(int i=0;i<values.length();i++) {
                    if(values.getJSONObject(i).has("text")) {
                        String ele = values.getJSONObject(i).getString("text");
                        list.add(ele);
                    }
                }
                map.put(title,list);
            }
        }
    }

    public static Map<String,List<String>> getPerson(JSONObject jsonObject){
        Map result = new HashMap<String,List<String>>();

        getHelper(result, jsonObject, "/type/object/name", "Name");

        getHelper(result, jsonObject, "/people/person/date_of_birth", "Birthday");

        getHelper(result, jsonObject, "/people/person/place_of_birth", "PlaceOfBirth");

        getHelper(result, jsonObject, "/people/deceased_person/place_of_death", "PlaceOfDeath");

        getHelper(result, jsonObject, "/people/deceased_person/date_of_death", "DateOfDirth");

        getHelper(result, jsonObject, "/people/deceased_person/cause_of_death", "CauseOfBirth");

        getHelper(result, jsonObject, "/people/person/sibling_s", "Siblings");

        getHelper(result, jsonObject, "/people/person/spouse_s", "Spouses");

        getHelper(result, jsonObject, "/common/topic/description", "Description");

        return result;
    }

    public Map<String,List<String>> getBusinessPerson(JSONObject jsonObject){
        Map result = new HashMap<String,List<String>>();
        /*
        if(jsonObject.has("/business/board_member/leader_of")) {
            JSONObject leadershipObject  = jsonObject.getJSONObject("/business/board_member/leader_of");
            if(leadershipObject.has("values")) {
                JSONArray leadershipValues = leadershipObject.getJSONArray("values");
                List <String> leadershipList = new LinkedList <String> ();
                for(int i=0;i<leadershipValues.length();i++) {
                    if(leadershipValues.getJSONObject(i).has("property")) {

                    }
                }
                result.put("leadership",leadershipList);
            }
        }
        */
        return null;
    }

    public static Map<String,List<String>> getActor(JSONObject jsonObject){
        Map result = new HashMap<String,List<String>>();
        if(jsonObject.has("/film/actor/film")) {
            JSONObject filmCharObject  = jsonObject.getJSONObject("/film/actor/film");
            if(filmCharObject.has("values")) {
                List <String> filmList = new LinkedList<String>();
                List <String> charList = new LinkedList<String>();
                JSONArray filmCharValues = filmCharObject.getJSONArray("values");
                for(int i=0;i<filmCharValues.length();i++) {
                    if(filmCharValues.getJSONObject(i).has("property")) {
                        JSONObject property = filmCharValues.getJSONObject(i).getJSONObject("property");
                        if(property.has("/film/performance/character")) {
                            JSONObject characters = property.getJSONObject("/film/performance/character");
                            if(characters.has("values")) {
                                JSONArray charactersValues = characters.getJSONArray("values");
                                for(int j=0;j<charactersValues.length();j++) {
                                    if(charactersValues.getJSONObject(j).has("text")) {
                                        String character = charactersValues.getJSONObject(j).getString("text");
                                        charList.add(character);
                                    }
                                }
                            }
                        }
                        if(property.has("/film/performance/film")) {
                            JSONObject films = property.getJSONObject("/film/performance/film");
                            if(films.has("values")) {
                                JSONArray filmsValues = films.getJSONArray("values");
                                for(int j=0;j<filmsValues.length();j++) {
                                    if(filmsValues.getJSONObject(j).has("text")) {
                                        String film = filmsValues.getJSONObject(j).getString("text");
                                        filmList.add(film);
                                    }
                                }
                            }
                        }
                    }
                }
                result.put("Film",filmList);
                result.put("Character",charList);
            }
        }
        return result;
    }


}
