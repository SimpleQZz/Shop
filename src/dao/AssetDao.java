package dao;

import domain.AssetInfo;
import domain.AssetSelling;

import java.util.List;

public interface AssetDao {
    List<AssetSelling> findAll();

    List<AssetInfo> findAssetByCompany(String company);

    public AssetInfo findAssetByNameAndCompany(String assetName, String company);

    void updateQuantity(String company, String assetName, String quantity);
}
