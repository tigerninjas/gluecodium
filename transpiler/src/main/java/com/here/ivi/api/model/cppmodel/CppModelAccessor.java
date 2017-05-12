package com.here.ivi.api.model.cppmodel;

import com.here.ivi.api.generator.common.cpp.CppNameRules;
import com.here.ivi.api.model.DefinedBy;
import com.here.ivi.api.model.FrancaElement;
import com.here.ivi.api.model.FrancaModel;
import navigation.CppStubSpec;

import java.util.List;
import java.util.Optional;

public class CppModelAccessor<DPA extends CppStubSpec.IDataPropertyAccessor> extends DefinedBy {

    public CppModelAccessor(FrancaElement<DPA> francaElement, CppNameRules rules,
                            FrancaModel<? extends CppStubSpec.IDataPropertyAccessor,
                                    ? extends CppStubSpec.IDataPropertyAccessor> fModel){

        super(francaElement.getFrancaTypeCollection(), francaElement.getModel().getFrancaModel());
        this.francaElement = francaElement;
        this.rules = rules;
        this.francaModel = fModel;
    }

    public DPA getAccessor() {
        return francaElement.getPropertyAccessor();
    }

    /**
     * Searches for an accessor object by the given type-collection and francaModel.
     *
     * @implNote Will search through all the loaded franca models.
     *
     * @param definer the definer containing type-collection and francaModel
     * @return The franca accessor for the given francaModel and type-collection
     */
    public Optional<? extends CppStubSpec.IDataPropertyAccessor> getAccessor(DefinedBy definer){
        return francaModel.find(definer).map(FrancaElement::getPropertyAccessor);
    }

    /**
     * @return the name rules
     */
    public CppNameRules getRules() {
        return rules;
    }

    public List<String> getNamespace() {
        return rules.packageToNamespace(getPackages());
    }

    private final FrancaElement<DPA> francaElement;
    private final CppNameRules rules;
    private final FrancaModel<? extends CppStubSpec.IDataPropertyAccessor,
                              ? extends CppStubSpec.IDataPropertyAccessor> francaModel;
}
