package org.openhds.webservice.resources;

import java.io.Serializable;

import org.openhds.controller.exception.ConstraintViolations;
import org.openhds.controller.service.InMigrationService;
import org.openhds.domain.model.FieldWorker;
import org.openhds.domain.model.InMigration;
import org.openhds.domain.model.Individual;
import org.openhds.domain.model.MigrationType;
import org.openhds.webservice.FieldBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/inmigrations")
public class InMigrationResource extends AbstractResource<InMigration> {

    private InMigrationService inMigrationService;
    private FieldBuilder fieldBuilder;

    @Autowired
    public InMigrationResource(InMigrationService inMigrationService, FieldBuilder fieldBuilder) {
        this.inMigrationService = inMigrationService;
        this.fieldBuilder = fieldBuilder;
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<? extends Serializable> insert(@RequestBody InMigration inMigration) {
        return createResource(inMigration);
    }

    @Override
    protected InMigration copy(InMigration item) {
        InMigration copy = new InMigration();
        copy.setCollectedBy(copyFieldWorker(item.getCollectedBy()));
        copy.setIndividual(copyIndividual(item.getIndividual()));
        copy.setMigType(item.getMigType());
        
        copy.setOrigin(item.getOrigin());
        copy.setReason(item.getReason());
        copy.setRecordedDate(item.getRecordedDate());
        copy.setVisit(copyVisit(item.getVisit()));
        
        return copy;
    }

    @Override
    protected void saveResource(InMigration item) throws ConstraintViolations {
        inMigrationService.createInMigration(item);
    }

    @Override
    protected void setReferencedFields(InMigration item, ConstraintViolations cv) {
        FieldWorker fw = fieldBuilder.referenceField(item.getCollectedBy(), cv);
        item.setCollectedBy(fw);
        item.setVisit(fieldBuilder.referenceField(item.getVisit(), cv));
        
        if (item.getMigType() == null || MigrationType.INTERNAL_INMIGRATION.equals(item.getMigType())) {
            item.setMigTypeInternal();
            item.setIndividual(fieldBuilder.referenceField(item.getIndividual(), cv, "Invalid individual id"));
        } else {
            Individual individual = item.getIndividual();
            individual.setMother(fieldBuilder.referenceField(individual.getMother(), cv, "Invalid mother id "));
            individual.setFather(fieldBuilder.referenceField(individual.getFather(), cv, "Invalid father id"));
            individual.setCollectedBy(fw);
        }
    }
}
