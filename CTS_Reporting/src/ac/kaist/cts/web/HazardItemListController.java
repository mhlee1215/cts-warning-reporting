package ac.kaist.cts.web;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ac.kaist.cts.domain.HazardItemList;
import ac.kaist.cts.service.HazardItemListService;

@Controller
public class HazardItemListController {
private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final HazardItemListService hazardItemListService = null;
	
	@RequestMapping("/getHazardItemListTop.do")
    public ModelAndView getHazardItemListTop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<HazardItemList> list = hazardItemListService.readTopList();

		String result = "";
		try {
			for(int i = 0 ; i < list.size() ; i++){
				HazardItemList item = list.get(i);
				if(i == 0)
					result = item.getId()+"_/"+item.getName();
				else
					result += ","+item.getId()+"_/"+item.getName();
			}
			logger.debug("resultHazardItemListTopStr : "+result);
			response.getWriter().append(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/getHazardItemListChildren.do")
    public ModelAndView getHazardItemListChildren(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int level = ServletRequestUtils.getIntParameter(request, "level", 0);
		int parent_id = ServletRequestUtils.getIntParameter(request, "parent_id", 0);
		
		HazardItemList query_item = new HazardItemList();
		query_item.setLevel(level+1);
		query_item.setParent_id(parent_id);
		
		List<HazardItemList> list = hazardItemListService.readChildList(query_item);

		String result = "";
		try {
			for(int i = 0 ; i < list.size() ; i++){
				HazardItemList item = list.get(i);
				if(i == 0)
					result = item.getId()+"_/"+item.getName();
				else
					result += ","+item.getId()+"_/"+item.getName();
			}
			logger.debug("resultHazardItemListTopStr : "+result);
			response.getWriter().append(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
