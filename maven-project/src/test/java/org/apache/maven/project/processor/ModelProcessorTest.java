package org.apache.maven.project.processor;

import java.util.ArrayList;

import junit.framework.TestCase;

import org.apache.maven.model.Dependency;
import org.apache.maven.model.DependencyManagement;
import org.apache.maven.model.Model;
import org.apache.maven.model.Parent;

public class ModelProcessorTest
    extends TestCase
{
    public void testModelProcessorVersion()
    {
        Model targetModel = new Model();
        Model childModel = new Model();
        childModel.setVersion( "1.0" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, false );
        assertEquals( "1.0", targetModel.getVersion() );
    }

    public void testModelProcessorVersionFromParent()
    {
        Model targetModel = new Model();
        Model childModel = new Model();

        Parent parent = new Parent();
        parent.setVersion( "1.0" );
        childModel.setParent( parent );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, false );
        assertEquals( "1.0", targetModel.getVersion() );
    }

    public void testModelProcessorGroupId()
    {
        Model targetModel = new Model();
        Model childModel = new Model();
        childModel.setGroupId( "gid" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, false );
        assertEquals( "gid", targetModel.getGroupId() );
    }

    public void testModelProcessorGroupIdFromParent()
    {
        Model targetModel = new Model();
        Model childModel = new Model();

        Parent parent = new Parent();
        parent.setGroupId( "gid" );
        childModel.setParent( parent );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, false );
        assertEquals( "gid", targetModel.getGroupId() );
    }

    public void testModelVersion()
    {
        Model targetModel = new Model();
        Model childModel = new Model();
        childModel.setModelVersion( "4.0" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, false );
        assertEquals( "4.0", targetModel.getModelVersion() );
    }

    public void testPackaging()
    {
        Model targetModel = new Model();
        Model childModel = new Model();
        childModel.setPackaging( "pom" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, false );
        assertEquals( "pom", targetModel.getPackaging() );
    }

    public void testNameSpecialized()
    {
        Model targetModel = new Model();
        Model childModel = new Model();
        childModel.setName( "name" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, true );
        assertEquals( "name", targetModel.getName() );
    }

    public void testNameNotSpecialized()
    {
        Model targetModel = new Model();
        Model childModel = new Model();
        childModel.setName( "name" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, false );
        assertNull( targetModel.getName() );
    }

    public void testDescriptionSpecialized()
    {
        Model targetModel = new Model();
        Model childModel = new Model();
        childModel.setDescription( "description" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, true );
        assertEquals( "description", targetModel.getDescription() );
    }

    public void testDescriptionNotSpecialized()
    {
        Model targetModel = new Model();
        Model childModel = new Model();
        childModel.setDescription( "description" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, false );
        assertNull( targetModel.getDescription() );
    }

    public void testInceptionYearFromChild()
    {
        Model targetModel = new Model();
        Model childModel = new Model();
        childModel.setInceptionYear( "2000" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, childModel, targetModel, false );
        assertEquals( "2000", targetModel.getInceptionYear() );

        childModel.setInceptionYear( "2001" );
        assertEquals( "2000", targetModel.getInceptionYear() );
    }

    public void testInceptionYearFromParent()
    {
        Model targetModel = new Model();

        Model childModel = new Model();

        Model parentModel = new Model();
        parentModel.setInceptionYear( "2000" );

        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( parentModel, childModel, targetModel, false );
        assertEquals( "2000", targetModel.getInceptionYear() );

        parentModel.setInceptionYear( "2001" );
        assertEquals( "2000", targetModel.getInceptionYear() );
    }
    
    public void testDependencyManagementChildCopy()
    {
        Model target= new Model();
        
        Model child = new Model();
        
        Dependency dependency = new Dependency();
        dependency.setArtifactId( "aid" );
        
        DependencyManagement mng = new DependencyManagement();
        mng.addDependency( dependency );
        
        child.setDependencyManagement( mng );
        
        ModelProcessor mp = new ModelProcessor( new ArrayList<Processor>() );
        mp.process( null, child, target, false );       
        
        assertNotNull(target.getDependencyManagement());
        
        assertEquals(1, target.getDependencyManagement().getDependencies().size());
        assertEquals("aid", target.getDependencyManagement().getDependencies().get( 0 ).getArtifactId());
        
    }
}