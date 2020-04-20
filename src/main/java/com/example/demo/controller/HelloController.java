package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.pojo.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;


@Api(value="controller",tags={"接口示例"})
@RestController
public class HelloController {

	//tags自定义标签，定义在接口上会生成新的标签，原有Controller的标签下依然存在，接口的tags值相同会在同一个标签下
	//notes接口描述信息
	@ApiOperation(value="你好",tags= "接口示例：get",notes="接口信息：无参数，返回值'你好' ")
	@GetMapping("/hello")
	public String hello() {
		return "你好";
	}
	
	//忽略此接口，文档不显示
	@ApiIgnore
	@PostMapping("getIgnore")
	public String getIgnore() {
		return "忽略的方法";
	}
	
	@ApiOperation(value="单个参数",tags= "接口示例：get",notes="接口信息：返回输入的参数值")
	@GetMapping("/getParamValue")
	public String getParamValue(@ApiParam(value="用户输入信息")@RequestParam String str) {
		return "您输入的参数为："+str;
	}
	
	@ApiOperation(value="多个参数",notes="接口信息：拼接多个参数值，返回字符串")
	@ApiImplicitParams({
		//paramType的query直接跟参数完成自动映射赋值
		//defaultValue默认值
		//dataType参数的数据类型 只作为标志说明，并没有实际验证
        @ApiImplicitParam(paramType="query",name="name",dataType="String",required=true,value="姓名",defaultValue="lili")
	})
	@PostMapping("/getParamsStr")
	public String getParamsStr(@RequestParam String name ,int age) {
		return "大家好我叫："+name+",今年"+age+"岁";
	}
	
	
	//只要我们的接口返回值中存在实体类，那么这个实体类就会被扫描到Swagger中
	@ApiOperation(value="User对象参数",notes="接口信息：返回User对象的用户名")
	@PostMapping("/getUserName")
	public String getUserName(@ApiParam(value="用户对象") @RequestBody User user) {
		return user.getUsername();
	}
	
	
	@ApiOperation(value="json格式参数",notes="接口信息：参数为JSONObject，获取参数中str的值进行返回")
	@PostMapping("/getJsonStr")
	public String getJsonStr(@ApiParam(value="json格式字符串") @RequestBody JSONObject json) {
		return json.getString("str");
	}
	
	@ApiOperation(value="返回值为map",notes="接口信息：根据传入的姓名返回map格式")
	@PostMapping("/getMap")
	public Map<String,String> getMap(@ApiParam(value="返回map对象的str值")@RequestParam String str) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("str", str);
		return map;
	}
	
	
	@ApiOperation(value="返回JSONObject",notes="接口信息：返回值为json格式时，无法自动生成model,可根据自定义的code = 0 查看解释")
	@ApiResponses({
		@ApiResponse(code = 0 ,message=" JSONObject =>'data':{'username':'String','id': int } "
				+ "username = 用户名"
				+ ",id = 编号")
	})
	@PostMapping("/getJsonResult")
	public JSONObject getJsonResult() {
		JSONObject json = new JSONObject();
		json.put("username", "swagger");
		json.put("id", 1);
		return json;
	}

}
