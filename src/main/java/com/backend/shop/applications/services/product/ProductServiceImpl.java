package com.backend.shop.applications.services.product;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backend.shop.applications.dto.product.ProductDTO;
import com.backend.shop.applications.dto.product.ProductVariantOptionDTO;
import com.backend.shop.applications.dto.product.request.ProductOptionRequest;
import com.backend.shop.applications.dto.product.request.ProductOptionValueRequest;
import com.backend.shop.applications.dto.product.request.ProductRequestDTO;
import com.backend.shop.applications.dto.product.request.ProductVariantRequestDTO;
import com.backend.shop.applications.interfaces.IFileService;
import com.backend.shop.applications.interfaces.IProductService;
import com.backend.shop.applications.mapper.ProductModelMapper;
import com.backend.shop.domains.datatable.DataTableFilter;
import com.backend.shop.domains.datatable.ResponseDataTable;
import com.backend.shop.domains.datatable.product.ProductFilter;
import com.backend.shop.domains.models.Product;
import com.backend.shop.domains.models.ProductOption;
import com.backend.shop.domains.models.ProductOptionValue;
import com.backend.shop.domains.models.ProductVariant;
import com.backend.shop.domains.models.ProductVariantOption;
import com.backend.shop.domains.models.VariantImage;
import com.backend.shop.domains.usecase.IProductUsecase;
import com.backend.shop.infrastructure.exceptions.BaseException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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
         //log.info("Product Id : " + product.getId());

         if (product.getMainImage() != null && product.getMainImage() instanceof MultipartFile) {
             MultipartFile mainImage = (MultipartFile) product.getMainImage();
             UUID uid = UUID.randomUUID();
             // สร้าง path สำหรับ main image
             String pathFile = fileService.createPath(mainImage,
                     Paths.get("product", uid.toString(), mainImage.getOriginalFilename()).toString());
             productModel.setMainImage(pathFile);
         } else {
             productModel.setMainImage((String) product.getMainImage());
         }
         // สร้าง Product Variants
         List<ProductOption> productOption = createProductOption(product.getProductOptions(), productModel);
         // ตั้งค่าผลิตภัณฑ์
         productModel.setProductOptions(productOption);
         //log.info("getProductOptions : " + productModel.getProductOptions().get(0).getProductOptionValues().get(0).getValue());
         productUsecase.createProduct(productModel);
    }

    private List<ProductOption> createProductOption(List<ProductOptionRequest> request,Product rootModel) throws IOException {
        List<ProductOption> productOptions = new ArrayList<>();

        for (ProductOptionRequest req: request) {
            ProductOption productOption = new ProductOption();
            productOption.setEnableImage(req.getEnableImage());
            productOption.setName(req.getName());
            productOption.setProduct(rootModel);
            if(req.getProductOptionValues() != null && !req.getProductOptionValues().isEmpty()){
                List<ProductOptionValue> productOptionValues = createProductOptionValue(req.getProductOptionValues(),productOption);
                productOption.setProductOptionValues(productOptionValues);
            }
            productOptions.add(productOption);
        }
        return productOptions;
    }

    private List<ProductOptionValue> createProductOptionValue(List<ProductOptionValueRequest> requests,ProductOption model) throws IOException {
        List<ProductOptionValue> productOptionValues = new ArrayList<>();

        for (ProductOptionValueRequest req : requests){
            ProductOptionValue productOptionValue = new ProductOptionValue();
            productOptionValue.setProductOption(model);
            productOptionValue.setValue(req.getValue());

            if(req.getImage() != null && req.getImage() instanceof MultipartFile){
                UUID uid = UUID.randomUUID();
                MultipartFile file = (MultipartFile) req.getImage();
                // สร้าง path สำหรับ variant image
                String pathFileVariant = fileService.createPath(file,Paths.get("product", uid.toString(), file.getOriginalFilename()).toString());
                productOptionValue.setImage(pathFileVariant);
            }
            productOptionValues.add(productOptionValue);
        }
        return productOptionValues;
    }

    @Override
    public void updateProduct(ProductRequestDTO product) throws IOException {
        Product productModel = productModelMapper.toModel(product);
        // //log.info("SIZE : " + product.getProductVariants().size());
        // UUID uid = UUID.randomUUID();
        // if (product.getMainImage() instanceof MultipartFile) {
        //     MultipartFile mainImage = (MultipartFile) product.getMainImage();
        //     // สร้าง path สำหรับ main image
        //     String pathFile = fileService.createPath(mainImage,
        //             Paths.get("product", uid.toString(), mainImage.getOriginalFilename()).toString());
        //     productModel.setMainImage(pathFile);
        // } else {
        //     productModel.setMainImage((String) product.getMainImage());
        // }

        // // สร้าง Product Variants
        // List<ProductVariant> productVariants = createProductVariants(product.getProductVariants(), productModel);
        // productModel.setProductVariants(productVariants);
        // productUsecase.createProduct(productModel);
    }

    @Override
    public ResponseDataTable<ProductDTO> filterProduct(DataTableFilter filter) {
        List<ProductDTO> productDTOS = productUsecase.filterProduct(filter).stream().map(productModelMapper::toDTO).toList();
        Long totalRecords = productUsecase.countProduct();
        return new ResponseDataTable<>(200, productDTOS, totalRecords, filter.getPage(), filter.getSize());
    }

    @Override
    public ResponseDataTable<ProductDTO> filterProduct(ProductFilter filter) {
        List<ProductDTO> productDTOS = productUsecase.filterProduct(filter).stream().map(productModelMapper::toDTO).toList();
        Long totalRecords = productUsecase.countProduct();
        return new ResponseDataTable<>(200, productDTOS, totalRecords, filter.getPage(), filter.getSize());
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
        UUID uid = UUID.randomUUID();
        if (url instanceof MultipartFile) {
            MultipartFile file = (MultipartFile) url;
            // สร้าง path สำหรับ variant image
            String pathFileVariant = fileService.createPath((MultipartFile) url,
                    Paths.get("product", uid.toString(), file.getOriginalFilename()).toString());
            variantImage.setUrl(pathFileVariant);
        } else {
            variantImage.setUrl((String) url);
        }

        // ตั้งค่า variantImage ให้กับ productVariant
        productVariant.setVariantImage(variantImage);
    }

}
