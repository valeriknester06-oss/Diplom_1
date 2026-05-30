package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient sauce;
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();

        bun = mock(Bun.class);
        sauce = mock(Ingredient.class);
        filling = mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(50f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);

        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(150f);
        when(filling.getType()).thenReturn(IngredientType.FILLING);
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientIncreasesIngredientsSizeTest() {
        burger.addIngredient(sauce);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void addIngredientAddsIngredientToListTest() {
        burger.addIngredient(sauce);

        assertEquals(sauce, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        burger.addIngredient(sauce);

        burger.removeIngredient(0);

        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientMovesFillingToFirstPositionTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(1, 0);

        assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientMovesSauceToSecondPositionTest() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(1, 0);

        assertEquals(sauce, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(400f, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        String expectedReceipt = String.format(
                "(==== %s ====)%n" +
                        "= %s %s =%n" +
                        "= %s %s =%n" +
                        "(==== %s ====)%n" +
                        "%n" +
                        "Price: %f%n",
                "black bun",
                "sauce", "hot sauce",
                "filling", "cutlet",
                "black bun",
                400f
        );

        assertEquals(expectedReceipt, burger.getReceipt());
    }
}