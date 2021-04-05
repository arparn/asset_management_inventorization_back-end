package ee.taltech.varadehaldamine.controller;


import ee.taltech.varadehaldamine.Rsql.AssetSearchCriteria;
import ee.taltech.varadehaldamine.model.Asset;
import ee.taltech.varadehaldamine.modelDTO.AssetInfo;
import ee.taltech.varadehaldamine.modelDTO.AssetInfoShort;
import ee.taltech.varadehaldamine.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("asset")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping("/filtered")
    @ResponseBody
    public ResponseEntity<Page<AssetInfoShort>> getAssets(
            AssetSearchCriteria assetSearchCriteria,
            @RequestParam(required = false, value = "page", defaultValue = "0") int page,
            // Using default value of 10 instead of a pathVariable
            @RequestParam(required = false, value = "size", defaultValue = "10") int size,

            @RequestParam(value = "order", required = false, defaultValue = "ASC") String order,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {
        return new ResponseEntity<>(assetService.getAssetsList(page, size, assetSearchCriteria, order, sortBy), HttpStatus.OK);
    }

    @GetMapping
    public List<AssetInfoShort> getAll() {
        return assetService.findAll();
    }

//    @GetMapping
//    public List<AssetInfoShort> getAll(@RequestParam(value = "name", required = false) String name,
//                                       @RequestParam(value = "active", required = false) Boolean active,
//                                       @RequestParam(value = "user", required = false) Long userId,
//                                       @RequestParam(value = "possessor", required = false) Long possessorId,
//                                       @RequestParam(value = "delicate", required = false) Boolean delicateCondition,
//                                       @RequestParam(value = "subclass", required = false) String subclass,
//                                       @RequestParam(value = "class", required = false) String mainClass,
//                                       @RequestParam(value = "building", required = false) String building,
//                                       @RequestParam(value = "institute", required = false) Integer institute,
//                                       @RequestParam(value = "division", required = false) Integer division,
//                                       @RequestParam(value = "subdivision", required = false) Integer subdivision) {
//        if (name != null) {
//            //some
//        }
//        if (active != null) {
//            //some
//        }
//        if (userId != null) {
//            //some
//        }
//        if (possessorId != null) {
//            //some
//        }
//        if (delicateCondition != null) {
//            //some
//        }
//        if (subclass != null) {
//            //some
//        }
//        if (mainClass != null) {
//            //some
//        }
//        if (building != null) {
//            //some
//        }
//        if (institute != null) {
//            //some
//        }
//        return assetService.findAll();
//    }


    @GetMapping("/{id}")
    public AssetInfo getAssetById(@PathVariable String id) {
        return assetService.getAssetById(id);
    }

    @PostMapping
    public ResponseEntity<Object> addAsset(@RequestBody AssetInfo asset) {
        if (assetService.addAsset(asset) != null) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }
}
