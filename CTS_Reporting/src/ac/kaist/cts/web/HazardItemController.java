package ac.kaist.cts.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ac.kaist.cts.domain.HazardItem;
import ac.kaist.cts.domain.HazardItemList;
import ac.kaist.cts.service.HazardItemListService;
import ac.kaist.cts.service.HazardItemService;

@Controller
public class HazardItemController {
private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final HazardItemService hazardItemService = null;
	
	@RequestMapping("/getHazardItems.do")
    public ModelAndView getHazardItemListTop(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HazardItem item = new HazardItem();
		item.setReport_id(1);
		
		
		int page = ServletRequestUtils.getIntParameter(request, "page", 0);// get the requested page
		int limit = ServletRequestUtils.getIntParameter(request, "rows", 0); // get how many rows we
																				// want to have into
																				// the grid
		String sidx = ServletRequestUtils.getStringParameter(request, "sidx", ""); // get index row
																					// - i.e. user
																					// click to sort
		String sord = ServletRequestUtils.getStringParameter(request, "sord", ""); // get the
																					// direction
		
		
		Vector<List<HazardItem> > hazard_list = hazardItemService.readHazardItems(item);
		int count = hazard_list.size();
		
		int total_pages = (int) Math.ceil((double) count / limit);
		int start = limit * page - limit; // do not put $limit*($page - 1)

		HashMap<String, String> params = new HashMap<String, String>();
		
		params.put("page", Integer.toString(page));
		params.put("limit", Integer.toString(limit));
		params.put("sidx", sidx);
		params.put("sord", sord);
		params.put("start", Integer.toString(start));

		
		
		
		
		
		/*for (int i = 0 ; i < list.size() ; i++){
			for (int j = 0 ; j < list.get(i).size() ; j++){
				System.out.print(list.get(i).get(j));
			}
		}*/

		
		//List<Task> taskList = taskService.readTaskList(params);
		//for(Task task : taskList)
		//	System.out.println(task);
		ModelAndView model = new ModelAndView("report/HazardList");
		model.addAllObjects(params);
		//model.addAllObjects(params);
		model.addObject("total_pages", total_pages);
		model.addObject("total_count", count);
		model.addObject("hazard_list", hazard_list);
		
		return model;
	}
}
