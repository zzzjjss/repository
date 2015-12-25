package com.uf.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uf.bean.Result;
import com.uf.entity.BuycarProductInfo;
import com.uf.entity.Customer;
import com.uf.entity.Product;
import com.uf.entity.ProductImage;
import com.uf.service.CustomerActionService;
import com.uf.service.ProductManageService;
import com.uf.util.FileUtil;
import com.uf.util.GsonExcludeStrategy;

@Controller
public class CustomerActionController {
  @Autowired
  private ProductManageService productManageService;
  @Autowired
  private CustomerActionService custermActionService;
  
  @RequestMapping("/index")
  public String productAdd(HttpServletRequest request,Model model) {
      Object obj=request.getSession().getAttribute("customer");
      if(obj==null&&obj instanceof Customer){
        model.addAttribute("customer", (Customer)obj);
      }
      return "index";
  }
  @RequestMapping("/customer/login")
  @ResponseBody
  public String login(@RequestParam Map<String,String> allRequestParams,Model model,HttpServletRequest request){
    Result result=new Result();
    String userName=allRequestParams.get("userName");
    String password=allRequestParams.get("password");
    Customer customer=custermActionService.findCustomerByUserName(userName);
    if(customer!=null&&password.equals(customer.getPassword())){
      request.getSession().setAttribute("customer", customer);
      result.setResult("ok");
    }else{
      result.setResult("fail");
      result.setMes("用户或密码错误");
    }
    Gson gson=new Gson();
    return gson.toJson(result);
    
  }
  
  @RequestMapping("/customer/addProductToBuycar")
  @ResponseBody
  public String addProductToBuycar(@RequestParam Map<String,String> allRequestParams,Model model,HttpServletRequest request){
    Gson gson=new Gson();
    Result result=new Result();
    String productId=allRequestParams.get("id");
    String count=allRequestParams.get("count");
    Customer customer=null;
    Object obj=request.getSession().getAttribute("customer");
    if(obj==null){
      result.setResult("fail");
      result.setMes("请登录");
      return gson.toJson(result);
    }else{
      customer=(Customer)obj;
    }
    Product product=productManageService.findProductById(Integer.parseInt(productId));
    if(product==null){
      result.setResult("fail");
      result.setMes("请选择产品");
      return gson.toJson(result);
    }
    BuycarProductInfo info= custermActionService.findCustomerBuycarProductInfoByProductId(product.getId(), customer.getId());
    if(info==null){
      info=new BuycarProductInfo();
    }
    info.setCount(Integer.parseInt(count));
    info.setCustomer(customer);
    info.setProduct(product);
    custermActionService.saveProductToBuyCar(info);
    result.setResult("ok");
    return gson.toJson(result);
  }
  @RequestMapping("/customer/removeProductFromBuycar")
  @ResponseBody
  public String removeProductFromBuycar(@RequestParam Map<String,String> allRequestParams,Model model,HttpServletRequest request){
    String id=allRequestParams.get("id");
    Gson gson=new Gson();
    Result result=new Result();
    Customer customer=null;
    Object obj=request.getSession().getAttribute("customer");
    if(obj==null){
      result.setResult("fail");
      result.setMes("请登录");
      return gson.toJson(result);
    }else{
      customer=(Customer)obj;
    }
    custermActionService.removeProductFromBuycar(customer.getId(), Integer.parseInt(id));
    result.setResult("ok");
    return gson.toJson(result);
  }
  
  @RequestMapping("/customer/queryBuycarProductInfo")
  @ResponseBody
  public String queryBuycarProductInfo(@RequestParam Map<String,String> allRequestParams,HttpServletRequest request){
    Gson gson=new GsonBuilder().setExclusionStrategies(new GsonExcludeStrategy()).create();
    List<BuycarProductInfo> buycarInfo=new ArrayList<BuycarProductInfo>();
    Customer customer=(Customer)request.getSession().getAttribute("customer");
    if(customer==null){
      return gson.toJson(buycarInfo);
    }
    buycarInfo=custermActionService.findCustomerBuycarProductInfo(customer.getId());
    String contextPath=request.getServletContext().getContextPath();
    File imgFolder=new File(request.getServletContext().getRealPath("/")+"img");
    if(buycarInfo!=null&&buycarInfo.size()>0){
      for(BuycarProductInfo prod:buycarInfo){
        setImgPathToProduct(prod.getProduct(),imgFolder,contextPath);
      }
    }
    return gson.toJson(buycarInfo);
  }
  
  private void setImgPathToProduct(Product pro,File imgFolder,String contextPath){
    File prodImg=new File(imgFolder,String.valueOf(pro.getId()));
    if(!prodImg.exists()||prodImg.listFiles().length==0){
        List<ProductImage> imgs=productManageService.findProductImages(pro.getId());
        if(imgs!=null&&imgs.size()>0){
          for(ProductImage img:imgs){
            File imgFile=new File(prodImg,img.getFileName());
            FileUtil.writeBytesToFile(img.getImage(), imgFile);
          }
        }
    }
    File imgs[]=prodImg.listFiles();
    if(imgs!=null&&imgs.length>0){
      List<String> imgPaths=new ArrayList<String>();
      for(File img:imgs){
        imgPaths.add(contextPath+"/img/"+pro.getId()+"/"+img.getName());
      }
      pro.setImgsPath(imgPaths);
    }
    
  }
}
