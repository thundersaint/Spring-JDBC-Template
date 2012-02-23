/**
 * @author Christian Nuesa
 * @version 1.0
 */

package com.temp.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import com.temp.datasource.MysqlDataSource;
import com.temp.entity.Product;
import com.temp.util.ExtractorUtil;

public class ProductRepository extends AbstractRepositoryImpl<Product>
{	
	private enum s {id, product, title, description, price, product_url, image_url, product_id, click_bid, promotion} ;
		
	public ProductRepository() throws Exception {
		super() ;
	}
	
	@Override
	public void init() throws Exception {
		jdbcTemplate = new JdbcTemplate(new MysqlDataSource()) ;
		simpleJdbcInsert = new ProductJdbcInsert(jdbcTemplate);
		mapper = new ProductRowMapper() ;
		
		insertRow = String.format("INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)", s.product, s.title, s.description, s.price, s.product_url, s.image_url, s.product_id, s.click_bid, s.promotion);
		findByIdQuery = String.format("SELECT * FROM %s WHERE %s = ?", s.product, s.id) ;
	}

	@Override
	public Product addRow(Product product)  throws DataAccessException
	{
		// TODO Auto-generated method stub
		Map<String, Object> args = new HashMap<String, Object>();
		args.put(s.title.name(), product.getTitle()) ;
		args.put(s.description.name(), product.getDescription() ) ;
		args.put(s.price.name(), product.getPrice()) ;
		args.put(s.product_url.name(), product.getProduct_url()) ;
		args.put(s.image_url.name(), product.getImage_url()) ;
		args.put(s.product_id.name(), product.getProduct_id()) ;
		args.put(s.click_bid.name(), product.getClick_bid()) ;
		args.put(s.promotion.name(), product.getPromotion()) ;

		Number id = executeAndReturnKey(args) ;
		
		if (id == null) return null ;

		return getRowById(id.longValue());
	}
	
	private class ProductRowMapper implements RowMapper<Product> {
		@Override
		public Product mapRow(ResultSet rs, int i) throws SQLException {
			return new Product(
					rs.getString(s.title.name()),		
					rs.getString(s.description.name()),
					rs.getDouble(s.price.name()),
					ExtractorUtil.getFromBlob(rs.getBlob(s.product_url.name())),
					ExtractorUtil.getFromBlob(rs.getBlob(s.image_url.name())),
					rs.getString(s.product_id.name()),
					rs.getDouble(s.click_bid.name()),
					rs.getString(s.promotion.name())
			) ;
		}
	}
	
	private class ProductJdbcInsert extends SimpleJdbcInsert {
		public ProductJdbcInsert(JdbcTemplate jt) {
			super(jt);
			setGeneratedKeyName("id");
			setTableName(s.product.name());
			setColumnNames(Arrays.asList(
					s.title.name(),
					s.description.name(),
					s.price.name(),
					s.product_url.name(),
					s.image_url.name(),
					s.product_id.name(),
					s.click_bid.name(),
					s.promotion.name()
			));		}
	}
}
