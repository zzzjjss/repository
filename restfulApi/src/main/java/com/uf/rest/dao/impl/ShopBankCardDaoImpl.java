package com.uf.rest.dao.impl;

import com.uf.rest.dao.ShopBankCardDao;
import com.uf.rest.entity.ShopBankCard;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
@Component("shopBankCardDao")
public class ShopBankCardDaoImpl extends CommonDaoImpl<ShopBankCard> implements ShopBankCardDao{

}
