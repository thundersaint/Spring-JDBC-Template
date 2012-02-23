/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.component;

import com.temp.entity.Product;

public class ProductMapper extends AbstractEntityMapper<Product> {
	
	boolean validateAndSet(int i, String data, Product product) {
			switch (i) {
			case 0 :
				if ((isNotEmpty(data) && isRightLength(data, Product.TITTLE_SIZE)) == false)
					return false ;
				
				product.setTitle(data) ;
				break ;
			case 1 :
				if (isNotEmpty(data) == false)
					return false ;
				
				product.setDescription(data) ;
				break ;
			case 2 :
				if (isNumber(data) == false)
					return false ;
				
				product.setPrice(Double.parseDouble(data)) ;
				break ;
			case 3 :
				if (isCorrectUrlFormat(data) == false)
					return false ;
				
				product.setProduct_url(data) ;
				break ;
			case 4 :
				if (isCorrectUrlFormat(data) == false)
					return false ;
				
				product.setImage_url(data) ;
				break ;
			case 5 :
				if (isRightLength(data, Product.PRODID_SIZE) == false)
					return false ;
				
				product.setProduct_id(data) ;
				break ;
			case 6 : // not req
				if (isNumber(data) == false)
					return false ;
				
				product.setClick_bid(Double.parseDouble(data)) ;
				break ;
			case 7 : // not req
				if (isRightLength(data, Product.PROMO_SIZE) == false)
					return false ;
				
				product.setPromotion(data) ;
				break ;
			default:
				break ;
		}
		return true ;
	}
}
