package com.here.ivi.api.generator.common;

import com.here.ivi.api.model.cppmodel.CppValue;
import org.franca.core.franca.FBasicTypeId;
import org.franca.core.franca.FInitializer;
import org.franca.core.franca.FTypeRef;
import org.franca.core.franca.FrancaFactory;

public class CppDefaultInitializer {
    public static CppValue map(FTypeRef it)
    {
        if (it.getDerived() != null)
        {
            return null;
        }
        else
        {
            FInitializer expr = FrancaFactory.eINSTANCE.createFInitializer();
            switch (it.getPredefined().getValue())
            {
                case FBasicTypeId.INT8_VALUE:
                case FBasicTypeId.INT16_VALUE:
                case FBasicTypeId.INT32_VALUE:
                case FBasicTypeId.INT64_VALUE:
                case FBasicTypeId.UINT8_VALUE:
                case FBasicTypeId.UINT16_VALUE:
                case FBasicTypeId.UINT32_VALUE:
                case FBasicTypeId.UINT64_VALUE:
                    return new CppValue( "0" );
                case FBasicTypeId.STRING_VALUE:
                    return null;
                case FBasicTypeId.BOOLEAN_VALUE:
                    return new CppValue( "false" );
                case FBasicTypeId.FLOAT_VALUE:
                    return new CppValue( "std::numeric_limits<float>::quiet_NaN()" );
                case FBasicTypeId.DOUBLE_VALUE:
                    return new CppValue( "std::numeric_limits<double>::quiet_NaN()" );
                case FBasicTypeId.BYTE_BUFFER_VALUE:
                    return null;
            }
        }

        return null;
    }

}
