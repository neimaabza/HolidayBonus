

/**
 * Calculate holiday bonuses given a ragged array of doubles which represent the
 * sales for each store in each category. It will also take in bonus amount for
 * the store with the highest sales in a category, the bonus amount for the
 * store with the lowest sales in a category and the bonus amount for all other
 * stores.
 *
 * @author Neima Abza
 * @version 3.0
 *
 */


public class HolidayBonus {
    /**
     * Calculates the holiday bonus of each store
     * @param data the two dimensional array of store sales
     * @param high bonus for the highest store in a category
     * @param low bonus for the lowest store in a category
     * @param other bonus for all other stores in a category
     * @return an array of bonus for each store
     */
    public static double[] calculateHolidayBonus(double[][] data, double high, double low, double other) {
        double[] bns = new double[data.length];

        // find max # of categories
        int maxCol = 0;
        for (int i = 0; i < data.length; i++) {
            if (maxCol < data[i].length) {
                maxCol = data[i].length;
            }
        }

        for (int j = 0; j < maxCol; j++) {
            // add bonus for high or low in each category
            int highBonusIndex = TwoDimRaggedArrayUtility.getHighestInColumnIndex(data, j);
            int lowBonusIndex = TwoDimRaggedArrayUtility.getLowestInColumnIndex(data, j);

            // sales must be positive to get high bonus
            if (TwoDimRaggedArrayUtility.getHighestInColumn(data, j) > 0) {
                bns[highBonusIndex] += high;
            }

            // sales must be positive and different from high to get low bonus
            if (TwoDimRaggedArrayUtility.getLowestInColumn(data, j) > 0 && highBonusIndex != lowBonusIndex) {
                bns[lowBonusIndex] += low;
            }

            // add OTHER bonus to the other stores unless not category available if sales
            // are positive
            for (int i = 0; i < data.length; i++) {
                if (j >= data[i].length || i == highBonusIndex || i == lowBonusIndex || data[i][j] <= 0) {
                    continue;
                } else {
                    bns[i] += other;
                }
            }
        }
        return bns;
    }

    /**
     *Calculates the total holiday bonus
     *@param data the two dimensional array of store sales
     * @param high bonus for the highest store in a category
     * @param low bonus for the lowest store in a category
     * @param other bonus for all other stores in a category
     * @return the total of holiday bonus
     */

    public static double calculateTotalHolidayBonus(double[][] data, double high, double low, double other) {

        double[] bonusArray = calculateHolidayBonus(data, high, low, other);
        double total = 0;

        for (double bonus : bonusArray) {
            total += bonus;
        }
        return total;
    }
}

