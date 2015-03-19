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

    private static List<String> getHelper(JSONObject jsonObject, String path){
        List <String> list = new LinkedList <String> ();
        if(jsonObject.has(path)) {
            JSONObject Object  = jsonObject.getJSONObject(path);
            if(Object.has("values")) {
                JSONArray values = Object.getJSONArray("values");
                for(int i=0;i<values.length();i++) {
                    if(values.getJSONObject(i).has("text")) {
                        String ele = values.getJSONObject(i).getString("text");
                        list.add(ele);
                    }
                }
            }
        }
        return list;
    }

    public static Map<String,List<String>> getPerson(JSONObject jsonObject){
        Map result = new HashMap<String,List<String>>();

        List <String> nameList = getHelper(jsonObject, "/type/object/name");
        result.put("Name",nameList);


        List <String> birthdayList = getHelper(jsonObject, "/people/person/date_of_birth");
        result.put("Birthday",birthdayList);

        List <String> birthPlaceList = getHelper(jsonObject, "/people/person/place_of_birth");
        result.put("PlaceOfBirth",birthPlaceList);

        List <String> deathPlaceList = getHelper(jsonObject, "/people/deceased_person/place_of_death");
        result.put("PlaceOfDeath",deathPlaceList);

        List <String> deathDateList = getHelper(jsonObject, "/people/deceased_person/date_of_death");
        result.put("DateOfDirth",deathDateList);

        List <String> deathCauseList = getHelper(jsonObject, "/people/deceased_person/cause_of_death");
        result.put("CauseOfBirth",deathCauseList);

        List <String> siblingsList = getHelper(jsonObject, "/people/person/sibling_s");
        result.put("Siblings",siblingsList);

        List <String> spousesList = getHelper(jsonObject, "/people/person/spouse_s");
        result.put("Spouses",spousesList);

        List <String> descrepitionList = getHelper(jsonObject, "/common/topic/description");
        result.put("Description",descrepitionList);


        return result;
    }


    public class  BusinessmanObject{
        List <String> From;
        List <String> To;
        List <String> Organization;
        List <String> Role;
        List <String> Title;
        List <String> Founded;
        public List <String> getFrom() {
            return From;
        }
        public List <String> getTo() {
            return To;
        }
        public List <String> getOrganization() {
            return Organization;
        }
        public List <String> getRole() {
            return Role;
        }
        public List <String> getTitle() {
            return Title;
        }
        public List <String> getFounded() {
            return Founded;
        }
    }

    public Map<String,List<BusinessmanObject>> getBusinessPerson(JSONObject jsonObject){
        Map result = new HashMap<String,List<BusinessmanObject>>();

        if(jsonObject.has("/business/board_member/leader_of")) {
            JSONObject leadershipObject  = jsonObject.getJSONObject("/business/board_member/leader_of");
            if(leadershipObject.has("values")) {
                JSONArray leadershipValues = leadershipObject.getJSONArray("values");
                List <BusinessmanObject> leadershipList = new LinkedList <BusinessmanObject> ();
                for(int i=0;i<leadershipValues.length();i++) {
                    if(leadershipValues.getJSONObject(i).has("property")) {
                        JSONObject leadership = leadershipValues.getJSONObject(i).getJSONObject("property");
                        BusinessmanObject element = new BusinessmanObject();

                        element.From = getHelper(leadership,"/organization/leadership/from");
                        element.To = getHelper(leadership,"/organization/leadership/to");
                        element.Organization = getHelper(leadership,"/organization/leadership/organization");
                        element.Role = getHelper(leadership,"/organization/leadership/role");
                        element.Title = getHelper(leadership,"/organization/leadership/title");

                        leadershipList.add(element);
                    }
                }
                result.put("Leadership",leadershipList);
            }
        }

        if(jsonObject.has("/business/board_member/organization_board_memberships")) {
            JSONObject boardMemberObject  = jsonObject.getJSONObject("/business/board_member/organization_board_memberships");
            if(boardMemberObject.has("values")) {
                JSONArray boardMemberValues = boardMemberObject.getJSONArray("values");
                List <BusinessmanObject> boardMemberList = new LinkedList <BusinessmanObject> ();
                for(int i=0;i<boardMemberValues.length();i++) {
                    if(boardMemberValues.getJSONObject(i).has("property")) {
                        JSONObject boardMember = boardMemberValues.getJSONObject(i).getJSONObject("property");
                        BusinessmanObject element = new BusinessmanObject();

                        element.From = getHelper(boardMember,"/organization/organization_board_membership/from");
                        element.To = getHelper(boardMember,"/organization/organization_board_membership/to");
                        element.Organization = getHelper(boardMember,"/organization/organization_board_membership/organization");
                        element.Role = getHelper(boardMember,"/organization/organization_board_membership/role");
                        element.Title = getHelper(boardMember,"/organization/organization_board_membership/title");

                        boardMemberList.add(element);
                    }
                }
                result.put("BoardMember",boardMemberList);
            }
        }

        List <String> foundedStringList = getHelper(jsonObject,"/organization/organization_founder/organizations_founded");
        List <BusinessmanObject> foundedList = new LinkedList <BusinessmanObject> ();
        for(String founded:foundedStringList) {
            BusinessmanObject businessmanObject = new BusinessmanObject();
            businessmanObject.Founded = new LinkedList<String> ();
            businessmanObject.Founded.add(founded);
            foundedList.add(businessmanObject);
        }
        result.put("Founded",foundedList);

        return result;
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
                        //charList = getHelper(property,"/film/performance/character");
                        //filmList = getHelper(property,"/film/performance/film");

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
