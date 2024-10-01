package de.riedelei.weather.city;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.riedelei.weather.util.CityMapper;
import de.riedelei.weather.util.CityMock;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;

// https://openweathermap.org/api/geocoding-api
@Service
public class CityService {
    private String placesUrl = "http://api.openweathermap.org/geo/1.0/direct?q=";

    private String jasonPlaces = "[{\"name\":\"Frankfurt\",\"local_names\":{\"he\":\"פרנקפורט על המיין\",\"yi\":\"פֿראנקפֿורט אם מיין\",\"gd\":\"Frankfurt am Main\",\"ie\":\"Frankfurt am Mann\",\"oc\":\"Francfòrt de Men\",\"sc\":\"Francuforte de su Menu\",\"la\":\"Francofurtum ad Moenum\",\"ar\":\"فرانكفورت أم ماين\",\"my\":\"ဖရန့်ဖွတ် အင်န် မိုင်း\",\"be\":\"Франкфурт-на-Майне\",\"is\":\"Frankfurt am Main\",\"vo\":\"Frankfurt len Main\",\"bs\":\"Frankfurt na Majni\",\"sv\":\"Frankfurt am Main\",\"gv\":\"Frankfurt am Main\",\"ro\":\"Frankfurt pe Main\",\"es\":\"Fráncfort del Meno\",\"bg\":\"Франкфурт на Майн\",\"cs\":\"Frankfurt nad Mohanem\",\"io\":\"Frankfurt am Main\",\"lt\":\"Frankfurtas prie Maino\",\"id\":\"Frankfurt am Main\",\"sd\":\"فرينڪفرٽ ايم مئن\",\"tr\":\"Main üzerindeki Frankfurt\",\"fa\":\"فرانکفورت\",\"ha\":\"Frankfurt\",\"sh\":\"Frankfurt na Majni\",\"no\":\"Frankfurt am Main\",\"fr\":\"Francfort-sur-le-Main\",\"kk\":\"Майндағы Франкфурт\",\"ko\":\"프랑크푸르트암마인\",\"zh\":\"美茵河畔法蘭克福\",\"jv\":\"Frankfurt am Main\",\"th\":\"ฟรังค์ฟวร์ทอัมไมน์\",\"kn\":\"ಫ್ರಾಂಕ್\u200Cಫರ್ಟ್ ಆಮ್ ಮೇನ್\",\"ga\":\"Frankfurt am Main\",\"vi\":\"Frankfurt am Main\",\"br\":\"Frankfurt am Main\",\"tt\":\"Майндагы Франкфурт\",\"da\":\"Frankfurt am Main\",\"na\":\"Frankfurt\",\"et\":\"Maini-äärne Frankfurt\",\"mn\":\"Майны Франкфурт\",\"ku\":\"Frankfurt\",\"nn\":\"Frankfurt am Main\",\"mr\":\"फ़्रांकफुर्ट आम माइन\",\"yo\":\"Ìlu Frankfọrtì\",\"pa\":\"ਫ਼ਰਾਂਫ਼ੁਰਟ ਆਮ ਮਾਈਨ\",\"gl\":\"Frankfurt\",\"ia\":\"Francoforte super le Moeno\",\"af\":\"Frankfurt am Main\",\"sq\":\"Frankfurti mbi Main\",\"tl\":\"Frankfurt am Main\",\"nl\":\"Frankfort aan de Main\",\"kv\":\"Майн вылын Франкфурт\",\"co\":\"Francuforte nant'à u Menu\",\"rm\":\"Frankfurt al Main\",\"ms\":\"Frankfurt am Main\",\"mk\":\"Франкфурт на Мајна\",\"el\":\"Φραγκφούρτη επί του Μάιν\",\"tw\":\"Frankfurt am Main\",\"fy\":\"Frankfurt am Main\",\"ta\":\"ஃபிராங்க்ஃபுர்ட் அம் மெய்ன்\",\"so\":\"Frankfurt\",\"kw\":\"Frankfurt am Main\",\"ur\":\"فرینکفرٹ\",\"ru\":\"Франкфурт-на-Майне\",\"hi\":\"फ्रैंकफर्ट ऐम माइन\",\"eo\":\"Frankfurto ĉe Majno\",\"hy\":\"Մայնի Ֆրանկֆուրտ\",\"uk\":\"Франкфурт-на-Майні\",\"xh\":\"Frankfurt am Main\",\"sl\":\"Frankfurt ob Majni\",\"ml\":\"ഫ്രാങ്ക്ഫുർട്ട് അം മൈൻ\",\"am\":\"ፍራንክፉርት አም ማይን\",\"tg\":\"Фра́нкфурт-Ам-Ма́йн\",\"ca\":\"Frankfurt del Main\",\"lv\":\"Frankfurte pie Mainas\",\"sw\":\"Frankfurt am Main\",\"an\":\"Frankfurt d'o Main\",\"sr\":\"Франкфурт на Мајни\",\"ky\":\"Майндагы-Франкфурт\",\"ht\":\"Frankfurt\",\"fo\":\"Frankfurt am Main\",\"en\":\"Frankfurt\",\"cy\":\"Frankfurt am Main\",\"os\":\"Франкфурт-Майныл\",\"pl\":\"Frankfurt nad Menem\",\"ka\":\"მაინის ფრანკფურტი\",\"ce\":\"Майн-тӀера-Франкфурт\",\"uz\":\"Frankfurtmayn\",\"sk\":\"Frankfurt nad Mohanom\",\"it\":\"Francoforte sul Meno\",\"hu\":\"Majna-Frankfurt\",\"bn\":\"ফ্রাঙ্কফুর্ট আম মাইন\",\"fi\":\"Frankfurt am Main\",\"ug\":\"Frankfort\",\"lb\":\"Frankfurt am Main\",\"ja\":\"フランクフルト・アム・マイン\",\"pt\":\"Frankfurt sobre o Meno\",\"li\":\"Fraankfort aon de Main\",\"se\":\"Frankfurt\",\"mg\":\"Franckfort/Main\",\"az\":\"Frankfurt-Mayn\",\"qu\":\"Frankfurt am Main\",\"hr\":\"Frankfurt na Majni\",\"eu\":\"Frankfurt am Main\",\"de\":\"Frankfurt am Main\",\"ba\":\"Майндағы Франкфурт\",\"cv\":\"Майнри Франкфурт\"},\"lat\":50.1106444,\"lon\":8.6820917,\"country\":\"DE\",\"state\":\"Hesse\"},{\"name\":\"Frankfurt (Oder)\",\"local_names\":{\"nl\":\"Frankfurt aan de Oder\",\"zh\":\"奥得河畔法兰克福\",\"ca\":\"Frankfurt de l'Oder\",\"de\":\"Frankfurt (Oder)\",\"fr\":\"Francfort-sur-l'Oder\",\"ru\":\"Франкфурт-на-Одере\",\"it\":\"Francoforte sull'Oder\",\"pl\":\"Frankfurt nad Odrą\",\"es\":\"Fráncfort del Óder\",\"uk\":\"Франкфурт-на-Одері\",\"cs\":\"Frankfurt nad Odrou\"},\"lat\":52.3412273,\"lon\":14.549452,\"country\":\"DE\",\"state\":\"Brandenburg\"},{\"name\":\"Słubice\",\"local_names\":{\"zh\":\"斯武比采\",\"de\":\"Słubice\",\"ru\":\"Слубице\",\"fa\":\"سوبیتسه\",\"lt\":\"Slubicės\",\"pl\":\"Słubice\",\"he\":\"סלוביצה\",\"ka\":\"სლუბიცე\",\"uk\":\"Слубіце\",\"hy\":\"Սլուբիցե\",\"ko\":\"스우비체\"},\"lat\":52.3557481,\"lon\":14.5662367,\"country\":\"PL\",\"state\":\"Lubusz Voivodeship\"},{\"name\":\"Frankfurt\",\"lat\":49.6806061,\"lon\":10.5267235,\"country\":\"DE\",\"state\":\"Bavaria\"},{\"name\":\"Frankfurt\",\"lat\":-24.6277761,\"lon\":30.7986057,\"country\":\"ZA\",\"state\":\"Mpumalanga\"}]";
    private String jasonString = "[{\"name\":\"Nuremberg\",\"local_names\":{\"es\":\"Núremberg\",\"sk\":\"Norimberg\",\"sr\":\"Нирнберг\",\"ko\":\"뉘른베르크\",\"en\":\"Nuremberg\",\"uk\":\"Нюрнберг\",\"ca\":\"Nuremberg\",\"nl\":\"Neurenberg\",\"la\":\"Norimberga\",\"de\":\"Nürnberg\",\"cs\":\"Norimberk\",\"fr\":\"Nuremberg\",\"ar\":\"نورنبرغ\",\"zh\":\"纽伦堡\",\"pl\":\"Norymberga\",\"hr\":\"Nürnberg\",\"it\":\"Norimberga\",\"fa\":\"نورنبرگ\",\"th\":\"เนือร์นแบร์ก\",\"hy\":\"Նյուրնբերգ\",\"pt\":\"Nuremberga\",\"he\":\"נירנברג\",\"lt\":\"Niurnbergas\",\"ka\":\"ნიურნბერგი\",\"ur\":\"نورنبرگ\",\"be\":\"Нюрнберг\",\"fi\":\"Nürnberg\",\"gl\":\"Nürnberg\",\"ja\":\"ニュルンベルク\",\"mk\":\"Нирнберг\",\"lv\":\"Nirnberga\",\"ru\":\"Нюрнберг\",\"el\":\"Νυρεμβέργη\"},\"lat\":49.453872,\"lon\":11.077298,\"country\":\"DE\",\"state\":\"Bavaria\"}]";
    public List<City> callCityData(String city) throws JsonProcessingException {
        // RestTemplate restTemplate = new RestTemplate();
        // setUrlWithLatLon(city);
        //
        // ResponseEntity<String> response
        //           = restTemplate.getForEntity(placesUrl, String.class);
        // var responseString = response.getBody().toString();
        // var cityMapper = new CityMapper();
        var cityMock = new CityMock();

        return cityMock.getAListOfCities();

        //return cityMapper.mapStringToCity(responseString);
    }

    public List<City> callOneCityData(String city) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        setUrlWithLatLonForOneCity(city);

        ResponseEntity<String> response
                = restTemplate.getForEntity(placesUrl, String.class);
        var responseString = response.getBody().toString();
        var cityMapper = new CityMapper();
        return cityMapper.mapStringToCity(responseString);
        //var cityMapper = new CityMapper();
        //return cityMapper.mapStringToCity(jasonPlaces);
    }

    private void setUrlWithLatLon(String city) {
        this.placesUrl = this.placesUrl.concat(city).concat("&limit=5&appid=c5b4da3f4057c95b48ffd41706fe58ea");
    }

    private void setUrlWithLatLonForOneCity(String city) {
        this.placesUrl = this.placesUrl.concat(city).concat("&limit=1&appid=c5b4da3f4057c95b48ffd41706fe58ea");
    }
}
