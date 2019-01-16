package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import hello.Calculations_repository;
import hello.History;
@Controller
public class HelloController {
	
	
	private long i=0;
	
	@Autowired
	Calculations_repository c;
	

	
	
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello(ModelMap model) 
	{
		
        return "hello";
    }
	
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	//@GetMapping("/hello")
    public String cal(ModelMap model, @RequestParam String value, @RequestParam String user_id) 
	{
		perform_calculations p = new perform_calculations();
		String error = p.isError(value);
		if(error.length()!=0)
		{
			model.put("value", "invalid expression");
			String stry="";
			for(History h1:c.findAll())
			{
				if(user_id.equals(h1.getUser_id()))
				stry+=h1.getUser_id()+" "+h1.getQuestion()+" , ";
			}
			model.put("his",stry);
			
	        return "hello";
		}
		int ans = p.result(value);
		
		model.put("value", value);
		model.put("ans",ans);
		
		History h = new History();
		h.setQuestion(value);
		h.setAnswer(ans);
		h.setId(i);
		i++;
		h.setUser_id(user_id);
		c.save(h);
		String stry="";
		for(History h1:c.findAll())
		{
			if(user_id.equals(h1.getUser_id()))
			stry+=h1.getUser_id()+" "+h1.getQuestion()+" , ";
		}
		model.put("his",stry);
		
        return "hello";
    }

}


