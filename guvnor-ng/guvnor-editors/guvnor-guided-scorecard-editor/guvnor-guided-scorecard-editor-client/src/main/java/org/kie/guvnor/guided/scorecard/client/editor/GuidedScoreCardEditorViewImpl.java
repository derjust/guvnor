/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.guvnor.guided.scorecard.client.editor;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import org.drools.guvnor.models.guided.scorecard.shared.ScoreCardModel;
import org.kie.guvnor.commons.ui.client.resources.i18n.CommonConstants;
import org.kie.guvnor.datamodel.oracle.DataModelOracle;
import org.kie.guvnor.guided.scorecard.client.widget.GuidedScoreCardEditor;

public class GuidedScoreCardEditorViewImpl
        extends Composite
        implements GuidedScoreCardEditorView {

    private GuidedScoreCardEditor editor = new GuidedScoreCardEditor();

    public GuidedScoreCardEditorViewImpl() {
        initWidget( editor );
    }

    @Override
    public void setContent( final ScoreCardModel model,
                            final DataModelOracle oracle ) {
        this.editor.setContent( model,
                                oracle );
    }

    @Override
    public ScoreCardModel getModel() {
        return this.editor.getModel();
    }

    @Override
    public boolean isDirty() {
        //TODO This editor does not currently support "isDirty"
        return false;
    }

    @Override
    public void setNotDirty() {
        //TODO This editor does not currently support "isDirty"
    }

    @Override
    public boolean confirmClose() {
        return Window.confirm( CommonConstants.INSTANCE.DiscardUnsavedData() );
    }

    @Override
    public void alertReadOnly() {
        Window.alert( CommonConstants.INSTANCE.CantSaveReadOnly() );
    }

}
