package pl.michaldurlak.AssetsInventoryManagement.assets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssetDetailsServiceImpl {

    @Autowired
    private AssetRepo assetRepo;

    public AssetDetailsServiceImpl(AssetRepo assetRepo){
        this.assetRepo = assetRepo;
    }



}
