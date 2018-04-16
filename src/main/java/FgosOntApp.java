import org.protege.editor.owl.ProtegeOWL;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import view.MainForm;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class FgosOntApp {
    public static void main(String[] args) {
//        String uri = "http://protege.cim3.net/file/pub/ontologies/travel/travel.owl";
//
//        //alternatively, you can specify a local path on your computer
//        //for the travel.owl ontology. Example:
//        //String uri = "file:///c:/Work/Projects/travel.owl"
//        OWLModel owlModel = ProtegeOWL.createJenaOWLModel();
//        owlModel.getNamespaceManager().setDefaultNamespace("http://hello.com#");
//        OWLNamedClass worldClass = owlModel.createOWLNamedClass("World");
//        System.out.println("Class URI: " + worldClass.getURI());
//
//        ListPanel listPanel = new ListPanel(destinationClass);
//        JFrame frame = new JFrame("Simple List Example");
//        Container cont = frame.getContentPane();
//        cont.setLayout(new BorderLayout());
//        cont.add(BorderLayout.CENTER, listPanel)
        try {
            shouldLoad();
        } catch (OWLOntologyCreationException e) {
            e.printStackTrace();
        }
         new MainForm();
    }

    public static void shouldLoad() throws OWLOntologyCreationException {
        // Get hold of an ontology manager

//        OWLDataFactory df = localPizza.getOWLOntologyManager().getOWLDataFactory();
//
//        OWLClass person = df.getOWLClass(IRI.create(IOR + "#"));
//        System.out.println(person);
//        System.out.println(person.getSubClasses(localPizza));
//        OWLDeclarationAxiom da = df.getOWLDeclarationAxiom(person);
        // We can always obtain the location where an ontology was loaded from
    }

}
