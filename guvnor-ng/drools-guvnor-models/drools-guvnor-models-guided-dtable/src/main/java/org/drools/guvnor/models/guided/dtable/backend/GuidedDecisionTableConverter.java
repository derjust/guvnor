package org.drools.guvnor.models.guided.dtable.backend;

import org.drools.guvnor.models.commons.backend.BaseConverter;
import org.drools.guvnor.models.commons.shared.rule.DSLSentence;
import org.drools.guvnor.models.commons.shared.rule.IAction;
import org.drools.guvnor.models.commons.shared.rule.IPattern;
import org.drools.guvnor.models.guided.dtable.backend.GuidedDTDRLPersistence;
import org.drools.guvnor.models.guided.dtable.backend.GuidedDTXMLPersistence;
import org.drools.guvnor.models.guided.dtable.shared.model.ActionCol52;
import org.drools.guvnor.models.guided.dtable.shared.model.BRLActionColumn;
import org.drools.guvnor.models.guided.dtable.shared.model.BRLConditionColumn;
import org.drools.guvnor.models.guided.dtable.shared.model.BaseColumn;
import org.drools.guvnor.models.guided.dtable.shared.model.CompositeColumn;
import org.drools.guvnor.models.guided.dtable.shared.model.GuidedDecisionTable52;
import org.kie.builder.impl.FormatConversionResult;

public class GuidedDecisionTableConverter extends BaseConverter {

    @Override
    public FormatConversionResult convert(String name, byte[] input) {
        String xml = new String(input);
        GuidedDecisionTable52 model = GuidedDTXMLPersistence.getInstance().unmarshal( xml );

        String drl = new StringBuilder()
                .append( getPackageDeclaration(name) )
                .append( model.getImports().toString() ).append( "\n" )
                .append( GuidedDTDRLPersistence.getInstance().marshal(model) ).toString();

        return new FormatConversionResult( getDestinationName(name, hasDSLSentences(model)), drl.getBytes() );
    }

    // Check is the model uses DSLSentences and hence requires expansion. THis code is copied from GuidedDecisionTableUtils.
    // GuidedDecisionTableUtils also handles data-types, enums etc and hence requires a DataModelOracle to function. Loading
    // a DataModelOracle just to determine whether the model has DSLs is an expensive operation and not needed here.
    public static boolean hasDSLSentences( final GuidedDecisionTable52 model ) {
        for ( CompositeColumn<? extends BaseColumn> column : model.getConditions() ) {
            if ( column instanceof BRLConditionColumn) {
                final BRLConditionColumn brlColumn = (BRLConditionColumn) column;
                for ( IPattern pattern : brlColumn.getDefinition() ) {
                    if ( pattern instanceof DSLSentence) {
                        return true;
                    }
                }
            }
        }
        for ( ActionCol52 column : model.getActionCols() ) {
            if ( column instanceof BRLActionColumn) {
                final BRLActionColumn brlColumn = (BRLActionColumn) column;
                for ( IAction action : brlColumn.getDefinition() ) {
                    if ( action instanceof DSLSentence ) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
