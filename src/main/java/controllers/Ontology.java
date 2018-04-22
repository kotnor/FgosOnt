package controllers;

import model.resmastering.ResMastering;
import model.reqtostructure.ReqToStructure;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

import java.io.File;
import java.util.List;

public class Ontology {
    private OWLOntologyManager manager;
    private OWLOntology fgosPattern;
    private IRI fgosIRI;
    private OWLDataFactory df;

    public Ontology() {
        manager = OWLManager.createOWLOntologyManager();
        File file = new File("D:/ontology.owl");
        // Now load the local copy
        IRI IOR = IRI.create("file:///D:/ontology.owl");
        fgosIRI= IRI.create("http://www.semanticweb.org/александр/ontologies/2016/11/my_ontology");
        try {
            fgosPattern = manager.loadOntologyFromOntologyDocument(IOR);
        } catch (OWLOntologyCreationException e) {

        }
        df = OWLManager.getOWLDataFactory();

    }

    private void addIndividualNumberWithComment(OWLClass owlClass, List<String> comments, String prefix) {
        for (int i = 0; i < comments.size(); i++) {
            OWLIndividual opkIndividual = df.getOWLNamedIndividual((IRI.create(fgosIRI + prefix + (i + 1))));
            OWLAnnotation commentAnno = df.getOWLAnnotation(df.getRDFSComment(), df.getOWLLiteral(comments.get(i)));
            OWLAxiom axiom = df.getOWLAnnotationAssertionAxiom(
                    opkIndividual.asOWLNamedIndividual().getIRI(), commentAnno);
            manager.applyChange(new AddAxiom(fgosPattern, axiom));
            OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(owlClass, opkIndividual);
            manager.addAxiom(fgosPattern, classAssertion);
        }
    }

    public void setResMastering(ResMastering resMastering) {
        IRI name = IRI.create(fgosIRI + "#ОПК");
        OWLClass opkClass = df.getOWLClass(name);
        OWLClass pkClass = df.getOWLClass(IRI.create(fgosIRI + "#ПК"));
        OWLClass okClass = df.getOWLClass(IRI.create(fgosIRI + "#ОК"));

        addIndividualNumberWithComment(opkClass, resMastering.commonProfCompetencies, "#ОПК-");
        addIndividualNumberWithComment(pkClass, resMastering.profCompetencies, "#ПК-");
        addIndividualNumberWithComment(okClass, resMastering.commonCompetencies, "#ОК-");

        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(fgosPattern);
        NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(opkClass, true);
        System.out.println("The Individuals of my class : ");

        for (OWLNamedIndividual i : instances.getFlattened()) {
            System.out.println(i.getIRI().getFragment());
            for (OWLAnnotationAssertionAxiom owlAnnotationAssertionAxiom: i.getAnnotationAssertionAxioms(fgosPattern)) {
                System.out.println(owlAnnotationAssertionAxiom.getValue());
            }
        }

        try {
            File output = new File("D:/saved_pizza2.owl");
            IRI documentIRI2 = IRI.create(output);
// save in RDF/XML
            manager.saveOntology(fgosPattern, documentIRI2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void assignOwlDataProp(OWLIndividual individual, String propertyName, OWLClass owlClassDomain, Double value) {
        OWLDataProperty vBasicDataProperty = df.getOWLDataProperty(IRI.create(fgosIRI + propertyName));
        OWLDataPropertyDomainAxiom owlDataPropertyDomainAxiom = df.getOWLDataPropertyDomainAxiom(vBasicDataProperty, owlClassDomain);
        OWLDataPropertyAssertionAxiom dataPropertyAssertionAxiom =
                df.getOWLDataPropertyAssertionAxiom(vBasicDataProperty, individual, value);
        manager.addAxiom(fgosPattern, dataPropertyAssertionAxiom);
        manager.addAxiom(fgosPattern, owlDataPropertyDomainAxiom);
    }

    private void fillFirstBlock(ReqToStructure reqToStructure) {
        OWLClass vBlockClass = df.getOWLClass(IRI.create(fgosIRI + "#Объем_блока"));
        OWLIndividual vBlockIndividual = df.getOWLNamedIndividual((IRI.create(fgosIRI + "#Экземпляр_объема_блока_1")));
        OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(vBlockClass, vBlockIndividual);
        manager.addAxiom(fgosPattern, classAssertion);
        assignOwlDataProp(vBlockIndividual, "#Имеет_минимальное_значение_объема_блока_1", vBlockClass, reqToStructure.getFirstBlock().getBlockOneRange().getMin());
        assignOwlDataProp(vBlockIndividual, "#Имеет_максимальное_значение_объема_блока_1", vBlockClass, reqToStructure.getFirstBlock().getBlockOneRange().getMax());
        fillBasicPart(reqToStructure);
        fillVarPart(reqToStructure);
    }

    private void fillSecondBlock(ReqToStructure reqToStructure) {
        OWLClass vBlockClass = df.getOWLClass(IRI.create(fgosIRI + "#Объем_блока"));
        OWLIndividual vBlockIndividual = df.getOWLNamedIndividual((IRI.create(fgosIRI + "#Экземпляр_объема_блока_2")));
        OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(vBlockClass, vBlockIndividual);
        manager.addAxiom(fgosPattern, classAssertion);
        assignOwlDataProp(vBlockIndividual, "#Имеет_минимальное_значение_объема_блока_2", vBlockClass, reqToStructure.getSecondBlock().getBlockTwoRange().getMin());
        assignOwlDataProp(vBlockIndividual, "#Имеет_максимальное_значение_объема_блока_2", vBlockClass, reqToStructure.getSecondBlock().getBlockTwoRange().getMax());
//        fillBasicPart(reqToStructure);
//        fillVarPart(reqToStructure);
    }

    private void fillThirdBlock(ReqToStructure reqToStructure) {
        OWLClass vBlockClass = df.getOWLClass(IRI.create(fgosIRI + "#Объем_блока"));
        OWLIndividual vBlockIndividual = df.getOWLNamedIndividual((IRI.create(fgosIRI + "#Экземпляр_объема_блока_3")));
        OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(vBlockClass, vBlockIndividual);
        manager.addAxiom(fgosPattern, classAssertion);
        assignOwlDataProp(vBlockIndividual, "#Имеет_минимальное_значение_объема_блока_3", vBlockClass, reqToStructure.getThirdBlock().getBlockThreeRange().getMin());
        assignOwlDataProp(vBlockIndividual, "#Имеет_максимальное_значение_объема_блока_3", vBlockClass, reqToStructure.getThirdBlock().getBlockThreeRange().getMax());
//        fillBasicPart(reqToStructure);
//        fillVarPart(reqToStructure);
    }

    private void fillBasicPart(ReqToStructure reqToStructure) {
        OWLClass vBasicClass = df.getOWLClass(IRI.create(fgosIRI + "#Объем_базовой_части"));
        OWLIndividual vBasicIndividual = df.getOWLNamedIndividual((IRI.create(fgosIRI + "#Экземпляр_объема_базовой_части")));
        OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(vBasicClass, vBasicIndividual);
        manager.addAxiom(fgosPattern, classAssertion);

        assignOwlDataProp(vBasicIndividual, "#Имеет_значение_объема_базовой_части", vBasicClass, reqToStructure.getFirstBlock().getBasicPartSize());
        assignOwlDataProp(vBasicIndividual, "#Имеет_минимальное_значение_объема_базовой_части", vBasicClass, reqToStructure.getFirstBlock().getBasicPartRange().getMin());
        assignOwlDataProp(vBasicIndividual, "#Имеет_максимальное_значение_объема_базовой_части", vBasicClass, reqToStructure.getFirstBlock().getBasicPartRange().getMax());

        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(fgosPattern);
        NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(vBasicClass, true);
        System.out.println("The Individuals of my class : ");

        for (OWLNamedIndividual i : instances.getFlattened()) {
            System.out.println(i.getIRI().getFragment());
            for (OWLAnnotationAssertionAxiom owlAnnotationAssertionAxiom: i.getAnnotationAssertionAxioms(fgosPattern)) {
                System.out.println(owlAnnotationAssertionAxiom.getValue());
            }
        }
    }

    private void fillVarPart(ReqToStructure reqToStructure) {
        OWLClass vVarClass = df.getOWLClass(IRI.create(fgosIRI + "#Объем_вариативной_части"));
        OWLIndividual vVarIndividual = df.getOWLNamedIndividual((IRI.create(fgosIRI + "#Экземпляр_объема_вариативной_части")));
        OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(vVarClass, vVarIndividual);
        manager.addAxiom(fgosPattern, classAssertion);

        assignOwlDataProp(vVarIndividual, "#Имеет_значение_объема_вариативной_части", vVarClass, reqToStructure.getFirstBlock().getVarPartSize());
        assignOwlDataProp(vVarIndividual, "#Имеет_минимальное_значение_объема_вариативной_части", vVarClass, reqToStructure.getFirstBlock().getVarPartRange().getMin());
        assignOwlDataProp(vVarIndividual, "#Имеет_максимальное_значение_объема_вариативной_части", vVarClass, reqToStructure.getFirstBlock().getVarPartRange().getMax());


        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(fgosPattern);
        NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(vVarClass, true);
        System.out.println("The Individuals of my class : ");

        for (OWLNamedIndividual i : instances.getFlattened()) {
            System.out.println(i.getIRI().getFragment());
            for (OWLAnnotationAssertionAxiom owlAnnotationAssertionAxiom: i.getAnnotationAssertionAxioms(fgosPattern)) {
                System.out.println(owlAnnotationAssertionAxiom.getValue());
            }
        }
    }

    private void fillVEducation(ReqToStructure reqToStructure) {
        OWLClass vBasicClass = df.getOWLClass(IRI.create(fgosIRI + "#Объем_обучения"));
        OWLIndividual vBasicIndividual = df.getOWLNamedIndividual((IRI.create(fgosIRI + "#Экземпляр_объема_обучения")));
        OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(vBasicClass, vBasicIndividual);
        manager.addAxiom(fgosPattern, classAssertion);

        assignOwlDataProp(vBasicIndividual, "#Имеет_значение_объема_обучения", vBasicClass, reqToStructure.getCommonSize().getMin());
    }

    public void setReqToStructure(ReqToStructure reqToStructure) {
        fillFirstBlock(reqToStructure);
        fillSecondBlock(reqToStructure);
        fillThirdBlock(reqToStructure);
        fillVEducation(reqToStructure);

        try {
            File output = new File("D:/saved_pizza2.owl");
            IRI documentIRI2 = IRI.create(output);
// save in RDF/XML
            manager.saveOntology(fgosPattern, documentIRI2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
