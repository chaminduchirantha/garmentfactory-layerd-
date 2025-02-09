package lk.ijse.gdse.factory_mvc_projecct.bo.custome.impl;

import lk.ijse.gdse.factory_mvc_projecct.Dao.DaoFactory;
import lk.ijse.gdse.factory_mvc_projecct.Dao.custome.ProductDetailDao;
import lk.ijse.gdse.factory_mvc_projecct.Entity.ProductDetail;
import lk.ijse.gdse.factory_mvc_projecct.bo.custome.ProductDetailBo;
import lk.ijse.gdse.factory_mvc_projecct.dto.ProductDetailDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDetailBoImpl implements ProductDetailBo {
    ProductDetailDao productDetailDao = (ProductDetailDao) DaoFactory.getInstance().getDao(DaoFactory.DaoType.PRODUCTDETAIL);

    @Override
    public boolean saveProductDetailsList(ArrayList<ProductDetailDto> productDetails) throws SQLException, ClassNotFoundException {
        ArrayList<ProductDetail> products = new ArrayList<ProductDetail>();

        for (ProductDetailDto dto : productDetails) {
            ProductDetail productDetail = new ProductDetail();

            productDetail.setItemId(dto.getItemId());
            productDetail.setProductId(dto.getProductId());
            productDetail.setProductDate(dto.getProductDate());
            productDetail.setItemPrice(dto.getItemPrice());
            productDetail.setItemQuantity(dto.getItemQuantity());
            productDetail.setItemName(dto.getItemName());
            productDetail.setTotalPrice(dto.getTotalPrice());


            products.add(productDetail);
        }
        return productDetailDao.saveProductDetailsList(products);

    }

    @Override
    public boolean saveProductDetail(ProductDetailDto productDetailDto) throws SQLException, ClassNotFoundException {
        ProductDetail productDetail = new ProductDetail();

        productDetail.setItemId(productDetailDto.getItemId());
        productDetail.setProductId(productDetailDto.getProductId());
        productDetail.setProductDate(productDetailDto.getProductDate());
        productDetail.setItemPrice(productDetailDto.getItemPrice());
        productDetail.setItemQuantity(productDetailDto.getItemQuantity());
        productDetail.setItemName(productDetailDto.getItemName());
        productDetail.setTotalPrice(productDetailDto.getTotalPrice());

        return productDetailDao.saveProductDetail(productDetail);


    }

}
