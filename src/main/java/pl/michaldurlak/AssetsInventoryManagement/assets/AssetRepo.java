package pl.michaldurlak.AssetsInventoryManagement.assets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michaldurlak.AssetsInventoryManagement.users.UserModel;

@Repository
public interface AssetRepo extends JpaRepository<AssetModel,Long> {


}
