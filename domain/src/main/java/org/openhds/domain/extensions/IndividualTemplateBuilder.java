package org.openhds.domain.extensions;

import org.openhds.domain.util.CalendarAdapter;

import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JBlock;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JConditional;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JDocComment;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;
import com.sun.codemodel.JWhileLoop;

public class IndividualTemplateBuilder implements ExtensionTemplate {
	
	JCodeModel jCodeModel;
	boolean individualTemplateBuilt = false;
	
	JFieldVar jfResidencies;
	
	IndividualTemplateBuilder(JCodeModel jCodeModel) {
		this.jCodeModel = jCodeModel;
	}
	
	public void buildTemplate(JDefinedClass jc) {
		
		JDocComment jDocComment = jc.javadoc();
		jDocComment.add("Generated by JCodeModel");
		
		jc._extends(org.openhds.domain.model.AuditableCollectedEntity.class);
		jc._implements(java.io.Serializable.class);
		
		buildClassAnnotations(jc);
		buildFieldsAndMethods(jc);
		buildAdditionalMethods(jc);
		
		individualTemplateBuilt = true;
	}
	
	public void buildFieldsAndMethods(JDefinedClass jc) {
		
		// serial uuid
		JFieldVar jfSerial = jc.field(JMod.PUBLIC | JMod.STATIC | JMod.FINAL, long.class, "serialVersionUID");
		jfSerial.init(JExpr.lit(9058114832143454609L));
		
		// extId
		JFieldVar jfExtId = jc.field(JMod.PRIVATE , java.lang.String.class, "extId");
		jfExtId.annotate(org.openhds.domain.constraint.Searchable.class);
		JAnnotationUse jaExtIdDesc = jfExtId.annotate(org.openhds.domain.annotations.Description.class);
		jaExtIdDesc.param("description", "External Id of the individual. This id is used internally.");
		
		// getter
		JMethod jmgExtId = jc.method(JMod.PUBLIC, java.lang.String.class, "getExtId");
		JBlock jmgExtIdBlock = jmgExtId.body();
		jmgExtIdBlock._return(jfExtId);
		
		// setter
		JMethod jmsExtId = jc.method(JMod.PUBLIC, void.class, "setExtId");
		JVar jvarExtId = jmsExtId.param(java.lang.String.class, "id");
		JBlock jmsExtIdBlock = jmsExtId.body();
		jmsExtIdBlock.assign(jfExtId, jvarExtId);
		
		// first name
		JFieldVar jfFirstName = jc.field(JMod.PRIVATE , java.lang.String.class, "firstName");
		jfFirstName.annotate(org.openhds.domain.constraint.CheckFieldNotBlank.class);
		jfFirstName.annotate(org.openhds.domain.constraint.Searchable.class);
		JAnnotationUse jaFirstNameDesc = jfFirstName.annotate(org.openhds.domain.annotations.Description.class);
		jaFirstNameDesc.param("description", "First name of the individual.");
		
		// getter
		JMethod jmgFirstName = jc.method(JMod.PUBLIC, java.lang.String.class, "getFirstName");
		JBlock jmgFirstNameBlock = jmgFirstName.body();
		jmgFirstNameBlock._return(jfFirstName);
		
		// setter
		JMethod jmsFirstName = jc.method(JMod.PUBLIC, void.class, "setFirstName");
		JVar jvarFirstName = jmsFirstName.param(java.lang.String.class, "name");
		JBlock jmsLocationNameBlock = jmsFirstName.body();
		jmsLocationNameBlock.assign(jfFirstName, jvarFirstName);
		
		// middle name
		JFieldVar jfMiddleName = jc.field(JMod.PRIVATE , java.lang.String.class, "middleName");
		jfMiddleName.annotate(org.openhds.domain.constraint.Searchable.class);
		JAnnotationUse jaMiddleNameDesc = jfMiddleName.annotate(org.openhds.domain.annotations.Description.class);
		jaMiddleNameDesc.param("description", "Middle name of the individual.");
		
		// getter
		JMethod jmgMiddleName = jc.method(JMod.PUBLIC, java.lang.String.class, "getMiddleName");
		JBlock jmgMiddleNameBlock = jmgMiddleName.body();
		jmgMiddleNameBlock._return(jfMiddleName);
		
		// setter
		JMethod jmsMiddleName = jc.method(JMod.PUBLIC, void.class, "setMiddleName");
		JVar jvarMiddleName = jmsMiddleName.param(java.lang.String.class, "name");
		JBlock jmsMiddleNameBlock = jmsMiddleName.body();
		jmsMiddleNameBlock.assign(jfMiddleName, jvarMiddleName);
		
		// last name
		JFieldVar jfLastName = jc.field(JMod.PRIVATE , java.lang.String.class, "lastName");
		jfLastName.annotate(org.openhds.domain.constraint.CheckFieldNotBlank.class);
		jfLastName.annotate(org.openhds.domain.constraint.Searchable.class);
		JAnnotationUse jaLastNameDesc = jfLastName.annotate(org.openhds.domain.annotations.Description.class);
		jaLastNameDesc.param("description", "Last name of the individual.");
		
		// getter
		JMethod jmgLastName = jc.method(JMod.PUBLIC, java.lang.String.class, "getLastName");
		JBlock jmgLastNameBlock = jmgLastName.body();
		jmgLastNameBlock._return(jfLastName);
		
		// setter
		JMethod jmsLastName = jc.method(JMod.PUBLIC, void.class, "setLastName");
		JVar jvarLastName = jmsLastName.param(java.lang.String.class, "name");
		JBlock jmsLastNameBlock = jmsLastName.body();
		jmsLastNameBlock.assign(jfLastName, jvarLastName);
		
		// gender
		JFieldVar jfGender = jc.field(JMod.PRIVATE , java.lang.String.class, "gender");
		JAnnotationUse jaGender = jfGender.annotate(org.openhds.domain.extensions.ExtensionStringConstraint.class);
		jaGender.param("constraint", "genderConstraint");
		jaGender.param("message", "Invalid Value for gender");
		jaGender.param("allowNull", true);
		JAnnotationUse jaLocationTypeDesc = jfGender.annotate(org.openhds.domain.annotations.Description.class);
		jaLocationTypeDesc.param("description", "The gender of the individual.");
		
		// getter
		JMethod jmgGender = jc.method(JMod.PUBLIC, java.lang.String.class, "getGender");
		JBlock jmgGenderBlock = jmgGender.body();
		jmgGenderBlock._return(jfGender);
		
		// setter
		JMethod jmsGender = jc.method(JMod.PUBLIC, void.class, "setGender");
		JVar jvarGender = jmsGender.param(java.lang.String.class, "sex");
		JBlock jmsGenderBlock = jmsGender.body();
		jmsGenderBlock.assign(jfGender, jvarGender);
		
		// dob
		JFieldVar jfDob = jc.field(JMod.PRIVATE , java.util.Calendar.class, "dob");
		JAnnotationUse jaDob = jfDob.annotate(javax.validation.constraints.Past.class);
		jaDob.param("message", "Date of birth must a date in the past");
		JAnnotationUse jaTemporal = jfDob.annotate(javax.persistence.Temporal.class);
		jaTemporal.param("value", javax.persistence.TemporalType.DATE);
		JAnnotationUse jaDobDesc = jfDob.annotate(org.openhds.domain.annotations.Description.class);
		jaDobDesc.param("description", "Birth date of the individual.");
		
		// getter
		JMethod jmgDob = jc.method(JMod.PUBLIC, java.util.Calendar.class, "getDob");
		JAnnotationUse jaXmlDob = jmgDob.annotate(javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.class);
		jaXmlDob.param("value", CalendarAdapter.class);
		JBlock jmgDobBlock = jmgDob.body();
		jmgDobBlock._return(jfDob);
		
		// setter
		JMethod jmsDob = jc.method(JMod.PUBLIC, void.class, "setDob");
		JVar jvarDob = jmsDob.param(java.util.Calendar.class, "date");
		JBlock jmsDobBlock = jmsDob.body();
		jmsDobBlock.assign(jfDob, jvarDob);
		
		// mother
		JFieldVar jfMother = jc.field(JMod.PRIVATE , org.openhds.domain.model.Individual.class, "mother");
		JAnnotationUse jaCheckFemale = jfMother.annotate(org.openhds.domain.constraint.CheckIndividualGenderFemale.class);
		jaCheckFemale.param("allowNull", true);
		jaCheckFemale.param("message", "The mother specified is not female gender");
		JAnnotationUse jaMotherCheckAge = jfMother.annotate(org.openhds.domain.constraint.CheckIndividualParentAge.class);
		jaMotherCheckAge.param("allowNull", true);
		jaMotherCheckAge.param("message", "The mother is younger than the minimum age required in order to be a parent");
		JAnnotationUse jaMotherNotVoided = jfMother.annotate(org.openhds.domain.constraint.CheckEntityNotVoided.class);
		jaMotherNotVoided.param("allowNull", true);
		jaMotherNotVoided.param("message", "The mother has been voided");
		JAnnotationUse jfMotherCascade = jfMother.annotate(javax.persistence.ManyToOne.class);
		JAnnotationArrayMember motherArray = jfMotherCascade.paramArray("cascade");
		motherArray.param(javax.persistence.CascadeType.MERGE);
		motherArray.param(javax.persistence.CascadeType.PERSIST);
		jfMotherCascade.param("targetEntity", org.openhds.domain.model.Individual.class);
		
		JAnnotationUse jaMotherDesc = jfMother.annotate(org.openhds.domain.annotations.Description.class);
		jaMotherDesc.param("description", "The individual's mother, identified by the external id.");
		
		// getter
		JMethod jmgMother = jc.method(JMod.PUBLIC, org.openhds.domain.model.Individual.class, "getMother");
		JBlock jmgMotherBlock = jmgMother.body();
		jmgMotherBlock._return(jfMother);
		
		// setter
		JMethod jmsMother = jc.method(JMod.PUBLIC, void.class, "setMother");
		JVar jvarMother = jmsMother.param(org.openhds.domain.model.Individual.class, "mom");
		JBlock jmsMotherBlock = jmsMother.body();
		jmsMotherBlock.assign(jfMother, jvarMother);
		
		// father
		JFieldVar jfFather = jc.field(JMod.PRIVATE , org.openhds.domain.model.Individual.class, "father");
		JAnnotationUse jaCheckMale = jfFather.annotate(org.openhds.domain.constraint.CheckIndividualGenderMale.class);
		jaCheckMale.param("allowNull", true);
		jaCheckMale.param("message", "The father specified is not male gender");
		JAnnotationUse jaFatherCheckAge = jfFather.annotate(org.openhds.domain.constraint.CheckIndividualParentAge.class);
		jaFatherCheckAge.param("allowNull", true);
		jaFatherCheckAge.param("message", "The father is younger than the minimum age required in order to be a parent");
		JAnnotationUse jaFatherNotVoided = jfFather.annotate(org.openhds.domain.constraint.CheckEntityNotVoided.class);
		jaFatherNotVoided.param("allowNull", true);
		jaFatherNotVoided.param("message", "The father has been voided");
		JAnnotationUse jfFatherCascade = jfFather.annotate(javax.persistence.ManyToOne.class);
		JAnnotationArrayMember fatherArray = jfFatherCascade.paramArray("cascade");
		fatherArray.param(javax.persistence.CascadeType.MERGE);
		fatherArray.param(javax.persistence.CascadeType.PERSIST);
		jfFatherCascade.param("targetEntity", org.openhds.domain.model.Individual.class);
		JAnnotationUse jaFatherDesc = jfFather.annotate(org.openhds.domain.annotations.Description.class);
		jaFatherDesc.param("description", "The individual's father, identified by the external id.");
		
		// getter
		JMethod jmgFather = jc.method(JMod.PUBLIC, org.openhds.domain.model.Individual.class, "getFather");
		JBlock jmgFatherBlock = jmgFather.body();
		jmgFatherBlock._return(jfFather);
		
		// setter
		JMethod jmsFather = jc.method(JMod.PUBLIC, void.class, "setFather");
		JVar jvarFather = jmsFather.param(org.openhds.domain.model.Individual.class, "dad");
		JBlock jmsFatherBlock = jmsFather.body();
		jmsFatherBlock.assign(jfFather, jvarFather);
		
		// dobAspect
		JFieldVar jfDobAspect = jc.field(JMod.PRIVATE , java.lang.String.class, "dobAspect");
		JAnnotationUse jaDobAspect = jfDobAspect.annotate(org.openhds.domain.extensions.ExtensionStringConstraint.class);
		jaDobAspect.param("constraint", "dobAspectConstraint");
		jaDobAspect.param("message", "Invalid Value for partial date");
		jaDobAspect.param("allowNull", true);
		JAnnotationUse jaDobAspectDesc = jfDobAspect.annotate(org.openhds.domain.annotations.Description.class);
		jaDobAspectDesc.param("description", "Identifer for determining if the birth date is partially known.");
		
		// getter
		JMethod jmgDobAspect = jc.method(JMod.PUBLIC, java.lang.String.class, "getDobAspect");
		JBlock jmgDobAspectBlock = jmgDobAspect.body();
		jmgDobAspectBlock._return(jfDobAspect);
		
		// setter
		JMethod jmsDobAspect = jc.method(JMod.PUBLIC, void.class, "setDobAspect");
		JVar jvarDobAspect = jmsDobAspect.param(java.lang.String.class, "aspect");
		JBlock jmsDobAspectBlock = jmsDobAspect.body();
		jmsDobAspectBlock.assign(jfDobAspect, jvarDobAspect);
		
		// residencies
		JClass basicSetResidencies = jCodeModel.ref(java.util.Set.class);
		basicSetResidencies = basicSetResidencies.narrow(org.openhds.domain.model.Residency.class);
		jfResidencies = jc.field(JMod.PRIVATE , basicSetResidencies, "allResidencies");
		JClass jResidenciesClassRef = jCodeModel.ref(java.util.HashSet.class);
		jResidenciesClassRef = jResidenciesClassRef.narrow(org.openhds.domain.model.Residency.class);
		jfResidencies.init(JExpr._new(jResidenciesClassRef));
		JAnnotationUse jaResidenciesTarget = jfResidencies.annotate(javax.persistence.OneToMany.class);
		jaResidenciesTarget.param("mappedBy", "individual");
		jaResidenciesTarget.param("cascade",  javax.persistence.CascadeType.ALL);
		JAnnotationUse jaResidenciesOrderBy = jfResidencies.annotate(javax.persistence.OrderBy.class);
		jaResidenciesOrderBy.param("value", "startDate");
		JAnnotationUse jaResidenciesDesc = jfResidencies.annotate(org.openhds.domain.annotations.Description.class);
		jaResidenciesDesc.param("description", "The set of all residencies that the individual may have.");
		
		// getter
		JMethod jmgResidencies = jc.method(JMod.PUBLIC, basicSetResidencies, "getAllResidencies");
		JBlock jmgResidenciesBlock = jmgResidencies.body();
		jmgResidenciesBlock._return(jfResidencies);
		
		// setter
		JMethod jmsResidencies = jc.method(JMod.PUBLIC, void.class, "setAllResidencies");
		JVar jvarResidencies = jmsResidencies.param(basicSetResidencies, "list");
		JBlock jmsResidenciesBlock = jmsResidencies.body();
		jmsResidenciesBlock.assign(jfResidencies, jvarResidencies);
		
		// allRelationships1
		JClass basicSetRelationships1 = jCodeModel.ref(java.util.Set.class);
		basicSetRelationships1 = basicSetRelationships1.narrow(org.openhds.domain.model.Relationship.class);
		JFieldVar jfRelationships1 = jc.field(JMod.PRIVATE , basicSetRelationships1, "allRelationships1");
		JClass jRelationship1ClassRef = jCodeModel.ref(java.util.HashSet.class);
		jRelationship1ClassRef = jRelationship1ClassRef.narrow(org.openhds.domain.model.Relationship.class);
		jfRelationships1.init(JExpr._new(jRelationship1ClassRef));
		JAnnotationUse jaRelationships1Target = jfRelationships1.annotate(javax.persistence.OneToMany.class);
		jaRelationships1Target.param("mappedBy", "individualA");
		jaRelationships1Target.param("cascade",  javax.persistence.CascadeType.ALL);
		JAnnotationUse jaRelationships1Desc = jfRelationships1.annotate(org.openhds.domain.annotations.Description.class);
		jaRelationships1Desc.param("description", "The set of all relationships that the individual may have with another individual.");
		
		// getter
		JMethod jmgRelationship1 = jc.method(JMod.PUBLIC, basicSetRelationships1, "getAllRelationships1");
		JBlock jmgRelationship1Block = jmgRelationship1.body();
		jmgRelationship1Block._return(jfRelationships1);
		
		// setter
		JMethod jmsRelationship1 = jc.method(JMod.PUBLIC, void.class, "setAllRelationships1");
		JVar jvarRelationship1 = jmsRelationship1.param(basicSetRelationships1, "list");
		JBlock jmsRelationship1Block = jmsRelationship1.body();
		jmsRelationship1Block.assign(jfRelationships1, jvarRelationship1);
		
		// allRelationships2
		JClass basicSetRelationships2 = jCodeModel.ref(java.util.Set.class);
		basicSetRelationships2 = basicSetRelationships2.narrow(org.openhds.domain.model.Relationship.class);
		JFieldVar jfRelationships2 = jc.field(JMod.PRIVATE , basicSetRelationships2, "allRelationships2");
		JClass jRelationship2ClassRef = jCodeModel.ref(java.util.HashSet.class);
		jRelationship2ClassRef = jRelationship2ClassRef.narrow(org.openhds.domain.model.Relationship.class);
		jfRelationships2.init(JExpr._new(jRelationship2ClassRef));
		JAnnotationUse jaRelationships2Target = jfRelationships2.annotate(javax.persistence.OneToMany.class);
		jaRelationships2Target.param("mappedBy", "individualB");
		jaRelationships2Target.param("cascade",  javax.persistence.CascadeType.ALL);
		JAnnotationUse jaRelationships2Desc = jfRelationships2.annotate(org.openhds.domain.annotations.Description.class);
		jaRelationships2Desc.param("description", "The set of all relationships where another individual may have with this individual.");
		
		// getter
		JMethod jmgRelationship2 = jc.method(JMod.PUBLIC, basicSetRelationships2, "getAllRelationships2");
		JBlock jmgRelationship2Block = jmgRelationship2.body();
		jmgRelationship2Block._return(jfRelationships2);
		
		// setter
		JMethod jmsRelationship2 = jc.method(JMod.PUBLIC, void.class, "setAllRelationships2");
		JVar jvarRelationship2 = jmsRelationship2.param(basicSetRelationships2, "list");
		JBlock jmsRelationship2Block = jmsRelationship2.body();
		jmsRelationship2Block.assign(jfRelationships2, jvarRelationship2);
		
		// allMemberships
		JClass basicSetMemberships = jCodeModel.ref(java.util.Set.class);
		basicSetMemberships = basicSetMemberships.narrow(org.openhds.domain.model.Membership.class);
		JFieldVar jfMemberships = jc.field(JMod.PRIVATE , basicSetMemberships, "allMemberships");
		JClass jMembershipClassRef = jCodeModel.ref(java.util.HashSet.class);
		jMembershipClassRef = jMembershipClassRef.narrow(org.openhds.domain.model.Membership.class);
		jfMemberships.init(JExpr._new(jMembershipClassRef));
		JAnnotationUse jaMembershipsTarget = jfMemberships.annotate(javax.persistence.OneToMany.class);
		jaMembershipsTarget.param("mappedBy", "individual");
		jaMembershipsTarget.param("cascade",  javax.persistence.CascadeType.ALL);
		JAnnotationUse jaMembershipsDesc = jfMemberships.annotate(org.openhds.domain.annotations.Description.class);
		jaMembershipsDesc.param("description", "The set of all memberships the individual is participating in.");
		
		// getter
		JMethod jmgMembership = jc.method(JMod.PUBLIC, basicSetMemberships, "getAllMemberships");
		JBlock jmgMembershipBlock = jmgMembership.body();
		jmgMembershipBlock._return(jfMemberships);
		
		// setter
		JMethod jmsMembership = jc.method(JMod.PUBLIC, void.class, "setAllMemberships");
		JVar jvarMembership = jmsMembership.param(basicSetMemberships, "list");
		JBlock jmsMembershipBlock = jmsMembership.body();
		jmsMembershipBlock.assign(jfMemberships, jvarMembership);
	}
	
	public void buildAdditionalMethods(JDefinedClass jc) {
		
		// getCurrentResidency
		JMethod jmgCurrentResidency = jc.method(JMod.PUBLIC, org.openhds.domain.model.Residency.class, "getCurrentResidency");
		
		// if statement
		JBlock jBlock = jmgCurrentResidency.body();
		JConditional condition = jBlock._if(JExpr.ref(jfResidencies, "size()").eq(JExpr.lit(0)));
		condition._then()._return(JExpr._null());
		
		// iterator
		JClass iterator = jCodeModel.ref(java.util.Iterator.class);
		iterator = iterator.narrow(org.openhds.domain.model.Residency.class);
		JVar jvIterator = jBlock.decl(iterator, "itr");
		jvIterator.init(JExpr.ref(jfResidencies, "iterator()"));
		
		// residency
		JClass residencies = jCodeModel.ref(org.openhds.domain.model.Residency.class);
		JVar jvResidencies = jBlock.decl(residencies, "residency");
		jvResidencies.init(JExpr._null());
		
		// while loop
		JWhileLoop whileLoop = jBlock._while(JExpr.ref(jvIterator, "hasNext()"));
		JBlock whileLoopBody = whileLoop.body();
		whileLoopBody.assign(jvResidencies, JExpr.ref(jvIterator, "next()"));
	
		jBlock._return(jvResidencies);	
	}
	
	public void buildClassAnnotations(JDefinedClass jc) {
		
		// create Description annotation
		JAnnotationUse jad = jc.annotate(org.openhds.domain.annotations.Description.class);
		jad.param("description", "An Individual represents one who is a part of the study. " +
			"Each Individual is identified by a uniquely generated external identifier which " +
			"the system uses internally. Information about the Individual such as name, gender, " +
			"date of birth, and parents are stored here. An Individual may be associated with many " +
			"Residencies, Relationships, and Memberships.");
		
		jc.annotate(org.openhds.domain.constraint.CheckMotherFatherNotIndividual.class);
		
		// create Entity annotation
		jc.annotate(javax.persistence.Entity.class);
		
		JAnnotationUse jat = jc.annotate(javax.persistence.Table.class);
		jat.param("name", "individual");
	}
}
