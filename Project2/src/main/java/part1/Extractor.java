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

    public Map<String,List<String>> getPerson(JSONObject jsonObject){
        Map result = new HashMap<String,List<String>>();

        if(jsonObject.has("/type/object/name")) {
            JSONObject nameObject  = jsonObject.getJSONObject("/type/object/name");
            if(nameObject.has("values")) {
                JSONArray nameValues = nameObject.getJSONArray("values");
                List <String> nameList = new LinkedList <String> ();
                for(int i=0;i<nameValues.length();i++) {
                    if(nameValues.getJSONObject(i).has("text")) {
                        String name = nameValues.getJSONObject(i).getString("text");
                        nameList.add(name);
                    }
                }
                result.put("Name",nameList);
            }
        }

        if(jsonObject.has("/people/person/date_of_birth")) {
            JSONObject birthdayObject  = jsonObject.getJSONObject("/people/person/date_of_birth");
            if(birthdayObject.has("values")) {
                JSONArray birthdayValues = birthdayObject.getJSONArray("values");
                List <String> birthdayList = new LinkedList<String>();
                for(int i=0;i<birthdayValues.length();i++) {
                    if(birthdayValues.getJSONObject(i).has("text")) {
                        String birthday = birthdayValues.getJSONObject(i).getString("text");
                        birthdayList.add(birthday);
                    }
                }
                result.put("Birthday",birthdayList);
            }
        }

        if(jsonObject.has("/people/person/place_of_birth")) {
            JSONObject birthPlaceObject  = jsonObject.getJSONObject("/people/person/place_of_birth");
            if(birthPlaceObject.has("values")) {
                JSONArray birthPlaceValues = birthPlaceObject.getJSONArray("values");
                List <String> birthPlaceList = new LinkedList<String>();
                for(int i=0;i<birthPlaceValues.length();i++) {
                    if(birthPlaceValues.getJSONObject(i).has("text")) {
                        String birthPlace = birthPlaceValues.getJSONObject(i).getString("text");
                        birthPlaceList.add(birthPlace);
                    }
                }
                result.put("PlaceOfBirth",birthPlaceList);
            }
        }

        if(jsonObject.has("/people/deceased_person/place_of_death")) {
            JSONObject deathPlaceObject  = jsonObject.getJSONObject("/people/deceased_person/place_of_death");
            if(deathPlaceObject.has("values")) {
                List <String> deathPlaceList = new LinkedList<String>();
                JSONArray deathPlaceValues = deathPlaceObject.getJSONArray("values");
                for(int i=0;i<deathPlaceValues.length();i++) {
                    if(deathPlaceValues.getJSONObject(i).has("text")) {
                        String deathPlace = deathPlaceValues.getJSONObject(i).getString("text");
                        deathPlaceList.add(deathPlace);
                    }
                }
                result.put("PlaceOfDeath",deathPlaceList);
            }
        }

        if(jsonObject.has("/people/deceased_person/date_of_death")) {
            JSONObject deathDateObject  = jsonObject.getJSONObject("/people/deceased_person/date_of_death");
            if(deathDateObject.has("values")) {
                List <String> deathDateList = new LinkedList<String>();
                JSONArray deathDateValues = deathDateObject.getJSONArray("values");
                for(int i=0;i<deathDateValues.length();i++) {
                    if(deathDateValues.getJSONObject(i).has("text")) {
                        String deathDate = deathDateValues.getJSONObject(i).getString("text");
                        deathDateList.add(deathDate);
                    }
                }
                result.put("DateOfDeath",deathDateList);
            }
        }

        if(jsonObject.has("/people/deceased_person/cause_of_death")) {
            JSONObject deathCauseObject  = jsonObject.getJSONObject("/people/deceased_person/cause_of_death");
            if(deathCauseObject.has("values")) {
                List <String> deathCauseList = new LinkedList<String>();
                JSONArray deathCauseValues = deathCauseObject.getJSONArray("values");
                for(int i=0;i<deathCauseValues.length();i++) {
                    if(deathCauseValues.getJSONObject(i).has("text")) {
                        String deathCause = deathCauseValues.getJSONObject(i).getString("text");
                        deathCauseList.add(deathCause);
                    }
                }
                result.put("CauseOfDeath",deathCauseList);
            }
        }

        if(jsonObject.has("/people/person/sibling_s")) {
            JSONObject siblingsObject  = jsonObject.getJSONObject("/people/person/sibling_s");
            if(siblingsObject.has("values")) {
                List <String> siblingsList = new LinkedList<String>();
                JSONArray siblingsValues = siblingsObject.getJSONArray("values");
                for(int i=0;i<siblingsValues.length();i++) {
                    if(siblingsValues.getJSONObject(i).has("text")) {
                        String sibling = siblingsValues.getJSONObject(i).getString("text");
                        siblingsList.add(sibling);
                    }
                }
                result.put("Siblings",siblingsList);
            }
        }

        if(jsonObject.has("/people/person/spouse_s")) {
            JSONObject spousesObject  = jsonObject.getJSONObject("/people/person/spouse_s");
            if(spousesObject.has("values")) {
                List <String> spousesList = new LinkedList<String>();
                JSONArray spousesValues = spousesObject.getJSONArray("values");
                for(int i=0;i<spousesValues.length();i++) {
                    if(spousesValues.getJSONObject(i).has("text")) {
                        String sibling = spousesValues.getJSONObject(i).getString("text");
                        spousesList.add(sibling);
                    }
                }
                result.put("Spouses",spousesList);
            }
        }

        if(jsonObject.has("/common/topic/description")) {
            JSONObject descriptionObject  = jsonObject.getJSONObject("/common/topic/description");
            if(descriptionObject.has("values")) {
                List <String> descriptionList = new LinkedList<String>();
                JSONArray descriptionValues = descriptionObject.getJSONArray("values");
                for(int i=0;i<descriptionValues.length();i++) {
                    if(descriptionValues.getJSONObject(i).has("text")) {
                        String sibling = descriptionValues.getJSONObject(i).getString("text");
                        descriptionList.add(sibling);
                    }
                }
                result.put("Description",descriptionList);
            }
        }

        return result;
    }

    public Map<String,List<String>> getBusinessPerson(JSONObject jsonObject){
        return null;
    }

    public Map<String,List<String>> getActor(JSONObject jsonObject){
        return null;
    }


}
