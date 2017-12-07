package jp.ics.az.vending

class VendingMachine {

    internal var quantityOfCoke = 5 // コーラの在庫数
    internal var quantityOfDietCoke = 5 // ダイエットコーラの在庫数
    internal var quantityOfTea = 5 // お茶の在庫数
    internal var numberOf100Yen = 10 // 100円玉の在庫
    internal var charge = 0 // お釣り

    /**
     * ジュースを購入する.
     *
     * @param i           投入金額. 100円と500円のみ受け付ける.
     * @param kindOfDrink ジュースの種類.
     * コーラ(`Juice.COKE`),ダイエットコーラ(`Juice.DIET_COKE`,お茶(`Juice.TEA`)が指定できる.
     * @return 指定したジュース. 在庫不足や釣り銭不足で買えなかった場合は `null` が返される.
     */
    fun buy(i: Int, kindOfDrink: Int): Drink? {
        // 100円と500円だけ受け付ける
        if (i != 100 && i != 500) {
            charge += i
            return null
        }

        if (kindOfDrink == Drink.COKE && quantityOfCoke == 0) {
            charge += i
            return null
        } else if (kindOfDrink == Drink.DIET_COKE && quantityOfDietCoke == 0) {
            charge += i
            return null
        } else if (kindOfDrink == Drink.TEA && quantityOfTea == 0) {
            charge += i
            return null
        }

        // 釣り銭不足
        if (i == 500 && numberOf100Yen < 4) {
            charge += i
            return null
        }

        if (i == 100) {
            // 100円玉を釣り銭に使える
            numberOf100Yen++
        } else if (i == 500) {
            // 400円のお釣り
            charge += i - 100
            // 100円玉を釣り銭に使える
            numberOf100Yen -= (i - 100) / 100
        }

        if (kindOfDrink == Drink.COKE) {
            quantityOfCoke--
        } else if (kindOfDrink == Drink.DIET_COKE) {
            quantityOfDietCoke--
        } else {
            quantityOfTea--
        }

        return Drink(kindOfDrink)
    }

    /**
     * お釣りを取り出す.
     *
     * @return お釣りの金額
     */
    fun refund(): Int {
        val result = charge
        charge = 0
        return result
    }

}
