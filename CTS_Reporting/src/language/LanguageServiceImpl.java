package language;

public class LanguageServiceImpl {
	public static LanguagePack getLangPack(String lang){
		if(lang == null)
			return new LanguagePackEng();
		else if("eng".compareToIgnoreCase(lang) == 0)
			return new LanguagePackEng();
		else if("kor".compareToIgnoreCase(lang) == 0)
			return new LanguagePackKor();
		else
			return new LanguagePackEng();
	}
}
