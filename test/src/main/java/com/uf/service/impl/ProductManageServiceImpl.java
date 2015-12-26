package com.uf.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uf.dao.ProductDao;
import com.uf.dao.ProductImgDao;
import com.uf.entity.Product;
import com.uf.entity.ProductImage;
import com.uf.searcher.SearchEngine;
import com.uf.service.ProductManageService;
import com.uf.util.PageQueryResult;
import com.uf.util.StringUtil;
@Service("productManageService")
public class ProductManageServiceImpl implements ProductManageService{
  @Autowired
  private ProductDao  productDao;
  @Autowired
  private ProductImgDao  productImgDao;
  @Autowired
  private SearchEngine  sercherEngine;
  public void addProduct(Product product,List<ProductImage>  imgs){
      productDao.insert(product);
      sercherEngine.addProductInfoToIndex(product);
      if(imgs!=null&&imgs.size()>0){
        for(ProductImage img:imgs){
          img.setProduct(product);
          productImgDao.insert(img);
        }
      }
  }
  
  public void updateProduct(Product product,List<ProductImage>  imgs){
    productDao.update(product);
    productImgDao.executeUpdateHql("delete from ProductImage img where img.product.id=? ", product.getId());
    if(imgs!=null&&imgs.size()>0){
      for(ProductImage img:imgs){
        img.setProduct(product);
        productImgDao.insert(img);
      }
    }
    sercherEngine.updateProductIndex(product);
  }
  public PageQueryResult<Product> findProducsByKeyword(String keyword,int pageSize,int pageIndex){
     if(StringUtil.isNullOrEmpty(keyword)){
       return productDao.findPagedProductByHql("select p from Product p ",null, pageSize, pageIndex);
     }else{
       List<Integer> ids=sercherEngine.searchProductIds(keyword);
       if(ids!=null&&ids.size()>0){
         Map<String, Object> idsParam=new HashMap<String,Object>();
         idsParam.put("ids", ids);
         return productDao.findPagedProductByHql("select p from Product p where p.id in (:ids) ", idsParam,pageSize, pageIndex);
       }else{
         return null;
       }
       
     }
  }
  public List<ProductImage> findProductImages(Integer productId){
    return productImgDao.findByHql("select pi from  ProductImage pi  where pi.product.id=?", productId);
  }
  public Product findProductById(Integer productId){
    return productDao.findById(Product.class, productId);
  }
  
  public void deleteProductById(Integer productId){
    productImgDao.executeUpdateHql("delete from ProductImage pi where pi.product.id=?", productId);
    productDao.executeUpdateHql("delete from Product p where p.id=?", productId);
    sercherEngine.deleteProductFromIndexById(productId);
    
  }
}
