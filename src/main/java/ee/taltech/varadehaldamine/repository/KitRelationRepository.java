package ee.taltech.varadehaldamine.repository;

import ee.taltech.varadehaldamine.model.KitRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KitRelationRepository extends JpaRepository<KitRelation, String> {

    KitRelation findKitRelationByComponentAssetId(String componentAssetId);
}