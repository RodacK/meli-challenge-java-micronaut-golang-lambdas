package com.meli.utils;

import com.meli.constants.Constants;
import com.meli.enums.MessagesEnums;
import com.meli.exceptions.QuasarException;
import com.meli.models.Position;
import io.micronaut.core.annotation.Introspected;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.DecompositionSolver;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;

@Introspected
public class Locator {

    public static Position getLocation(float... distances){
        for (float distance : distances) {
            if(distance < 0){
                throw new QuasarException(MessagesEnums.INVALID_DISTANCES.getAll());
            }
        }
        //configurar las ecuaciones de circunferencia
        CircleStandardEquation circle1 = new CircleStandardEquation(Constants.KENOVI_POSITION[0], Constants.KENOVI_POSITION[1],distances[0]);
        CircleStandardEquation circle2 = new CircleStandardEquation(Constants.SKYWALKER_POSITION[0], Constants.SKYWALKER_POSITION[1],distances[1]);
        CircleStandardEquation circle3 = new CircleStandardEquation(Constants.SATO_POSITION[0], Constants.SATO_POSITION[1],distances[1]);
        //obtener las ecuaciones extendidas
        String eq1 = circle1.getExpandedEquation();
        String eq2 = circle2.getExpandedEquation();
        String eq3 = circle3.getExpandedEquation();
        //obtener coefcientes
        double[] rootAxis1 = NoLinealEquationsUtils.getCoefficients(eq2,eq1);
        double[] rootAxis2 = NoLinealEquationsUtils.getCoefficients(eq3,eq1);
        //tambien se hubiera podido por gauss-jordan pero aprovechemos que Apache math nos da la descomposicion
        RealMatrix coefficients = new Array2DRowRealMatrix(new double[][] { {rootAxis1[0],rootAxis1[1]}, { rootAxis2[0],rootAxis2[1] } },false);
        DecompositionSolver solver = new LUDecomposition(coefficients).getSolver();
        RealVector constants = new ArrayRealVector(new double[] { rootAxis1[2],rootAxis2[2]}, false);
        RealVector solution = solver.solve(constants);
        return Position.builder().x(solution.getEntry(0)).y(solution.getEntry(1)).build();
    }
}
