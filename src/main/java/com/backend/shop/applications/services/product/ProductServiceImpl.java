package com.backend.shop.applications.services.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.backend.shop.applications.mapper.ProductModelMapper;
import com.backend.shop.domains.models.Product;
import com.backend.shop.domains.filter.ProductFilter;
import com.backend.shop.domains.models.ProductOptionValue;
import com.backend.shop.domains.models.ProductVariant;
import com.backend.shop.domains.models.ProductVariantOption;
import com.backend.shop.domains.models.VariantImage;
import com.backend.shop.infrastructure.exceptions.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.dto.product.ProductVariantOptionDTO;
import com.backend.shop.applications.dto.product.request.ProductRequestDTO;
import com.backend.shop.applications.dto.product.request.ProductVariantRequestDTO;
import com.backend.shop.applications.interfaces.IFileService;
import com.backend.shop.applications.interfaces.IProductService;
import com.backend.shop.domains.usecase.IProductUsecase;

@Service
public class ProductServiceImpl implements IProductService {

    private final IProductUsecase productUsecase;
    private final ProductModelMapper productModelMapper;
    private final IFileService fileService;

    public ProductServiceImpl(IProductUsecase productUsecase, ProductModelMapper productModelMapper,
            IFileService fileService) {
        this.productUsecase = productUsecase;
        this.productModelMapper = productModelMapper;
        this.fileService = fileService;

    }

    @Override
    public ProductDTO getProductByName(String name) {
        Product product = productUsecase.getProductByName(name);
        if (product == null) {
            throw new BaseException("Product name not found.", HttpStatus.BAD_REQUEST);
        }
        return productModelMapper.toDTO(product);
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Product product = productUsecase.getProductById(id);
        if (product == null) {
            throw new BaseException("Product name not found.", HttpStatus.BAD_REQUEST);
        }
        return productModelMapper.toDTO(product);
    }

    @Override
    public List<ProductDTO> getAllProduct(int page, int size) {
        return productUsecase.getAllProducts(page, size).stream().map(productModelMapper::toDTO).toList();
    }



    @Override
    public void deleteProduct(Long id) {
        productUsecase.deleteProductById(id);
    }

    @Override
    public void createProduct(ProductRequestDTO product) throws IOException {
        Product productModel = productModelMapper.toModel(product);
        System.out.println("Product Id : " + product.getId());

        if (product.getMainImage() instanceof MultipartFile) {
            MultipartFile mainImage = (MultipartFile) product.getMainImage();
            // สร้าง path สำหรับ main image
            String pathFile = fileService.createPath(mainImage);
            productModel.setMainImage(pathFile);
        } else {
            productModel.setMainImage((String) product.getMainImage());
        }

        // สร้าง Product Variants
        List<ProductVariant> productVariants = createProductVariants(product.getProductVariants(), productModel);

        // ตั้งค่าผลิตภัณฑ์
        productModel.setProductVariants(productVariants);

        System.out.println("getProductVariantOptions : " + productModel.getProductVariants().get(0)
                .getProductVariantOptions().get(0).getId());
        productUsecase.createProduct(productModel);
    }

    @Override
    public void updateProduct(ProductRequestDTO product) throws IOException {
        Product productModel = productModelMapper.toModel(product);
        System.out.println("SIZE : " + product.getProductVariants().size());

        if (product.getMainImage() instanceof MultipartFile) {
            MultipartFile mainImage = (MultipartFile) product.getMainImage();
            // สร้าง path สำหรับ main image
            String pathFile = fileService.createPath(mainImage);
            productModel.setMainImage(pathFile);
        } else {
            productModel.setMainImage((String) product.getMainImage());
        }

        // สร้าง Product Variants
        List<ProductVariant> productVariants = createProductVariants(product.getProductVariants(), productModel);

        productModel.setProductVariants(productVariants);

        productUsecase.createProduct(productModel);
    }

    @Override
    public List<ProductDTO> filterProduct(ProductFilter filter){

        return productUsecase.filterProduct(filter).stream().map(productModelMapper::toDTO).toList();
    }

    private List<ProductVariant> createProductVariants(List<ProductVariantRequestDTO> productVariantDTOs,
            Product productModel) throws IOException {
        List<ProductVariant> productVariants = new ArrayList<>();

        for (ProductVariantRequestDTO productVariantDTO : productVariantDTOs) {
            ProductVariant productVariant = new ProductVariant();
            productVariant.setId(productVariantDTO.getId());
            productVariant.setProduct(productModel);
            productVariant.setSku(productVariantDTO.getSku());
            productVariant.setPrice(productVariantDTO.getPrice());
            productVariant.setStock(productVariantDTO.getStock());

            // สร้าง Product Variant Options
            createProductVariantOptions(productVariantDTO.getProductVariantOptions(), productVariant);

            // สร้าง Variant Image
            createVariantImage(productVariantDTO, productVariant);

            // เพิ่ม ProductVariant ไปยัง List
            productVariants.add(productVariant);
        }

        return productVariants;
    }

    private void createProductVariantOptions(List<ProductVariantOptionDTO> productVariantOptionsDTO,
            ProductVariant productVariant) {
        if (!productVariantOptionsDTO.isEmpty()) {
            for (ProductVariantOptionDTO variantOption : productVariantOptionsDTO) {
                ProductVariantOption productVariantOption = new ProductVariantOption();
                productVariantOption.setId(variantOption.getId());
                productVariantOption.setProductVariant(productVariant);

                ProductOptionValue productOptionValue = new ProductOptionValue();
                productOptionValue.setId(variantOption.getProductOptionValue().getId());
                productOptionValue.setValue(variantOption.getProductOptionValue().getValue());

                productVariantOption.setProductOptionValue(productOptionValue);
                productVariant.getProductVariantOptions().add(productVariantOption);
            }
        }
    }

    private void createVariantImage(ProductVariantRequestDTO productVariantDTO, ProductVariant productVariant)
            throws IOException {
        VariantImage variantImage = new VariantImage();
        variantImage.setId(productVariantDTO.getVariantImage().getId());
        variantImage.setProductVariant(productVariant);
        
        Object url = productVariantDTO.getVariantImage().getUrl();

        if (url instanceof MultipartFile) {
            // สร้าง path สำหรับ variant image
            String pathFileVariant = fileService.createPath((MultipartFile)url);
            variantImage.setUrl(pathFileVariant);
        }else{
            variantImage.setUrl((String)url);
        }

        // ตั้งค่า variantImage ให้กับ productVariant
        productVariant.setVariantImage(variantImage);
    }

}
