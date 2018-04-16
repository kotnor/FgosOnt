package controllers;

import model.ResMastering;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.vocab.OWLRDFVocabulary;

import java.io.File;
import java.io.IOException;
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
//        for (int i = 0; i < resMastering.commonProfCompetencies.size(); i++) {
//            OWLIndividual opkIndividual = df.getOWLNamedIndividual((IRI.create(fgosIRI + "#ОПК-" + (i + 1))));
//            OWLAnnotation commentAnno = df.getOWLAnnotation(df.getRDFSComment(), df.getOWLLiteral(resMastering.commonProfCompetencies.get(i)));
//            OWLAxiom axiom = df.getOWLAnnotationAssertionAxiom(
//                    opkIndividual.asOWLNamedIndividual().getIRI(), commentAnno);
//            manager.applyChange(new AddAxiom(fgosPattern, axiom));
//            OWLClassAssertionAxiom classAssertion = df.getOWLClassAssertionAxiom(opkClass, opkIndividual);
//            manager.addAxiom(fgosPattern, classAssertion);
//        }
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
            File output = new File("D:/saved_pizza.owl");
            IRI documentIRI2 = IRI.create(output);
// save in RDF/XML
            manager.saveOntology(fgosPattern, documentIRI2);
        } catch (Exception e) {
            e.printStackTrace();
        }


//        OWLDataProperty countYears = df.getOWLDataProperty(IRI.create(fgos_iri + "#ЗначениеСрокаОбучения"));
//        OWLAxiom assertion = df.getOWLDataPropertyAssertionAxiom(countYears, topping2, 2);
//        System.out.println(topping);
//        System.out.println("Loaded ontology: " + fgosPattern.getAxioms());
//        System.out.println("Axioms: "+fgosPattern.getAxiomCount()+", Format:"+manager.getOntologyFormat(fgosPattern));
//        System.out.println((fgosPattern.getClassesInSignature().toArray())[0]);
    }
}
