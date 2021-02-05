/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planetprogrammig;

import javax.swing.text.*;

public class TabSizeEditorKit extends StyledEditorKit {

    public static final int TAB_SIZE = 36;

    @Override
    public ViewFactory getViewFactory() {
        return new MyViewFactory();
    }

    public static class MyViewFactory implements ViewFactory {

        @Override
        public View create(Element elem) {
            String kind = elem.getName();
            if (kind != null) {
                if (kind.equals(AbstractDocument.ContentElementName)) {
                    return new LabelView(elem);
                } else if (kind.equals(AbstractDocument.ParagraphElementName)) {
                    return new CustomTabParagraphView(elem);
                } else if (kind.equals(AbstractDocument.SectionElementName)) {
                    return new BoxView(elem, View.Y_AXIS);
                } else if (kind.equals(StyleConstants.ComponentElementName)) {
                    return new ComponentView(elem);
                } else if (kind.equals(StyleConstants.IconElementName)) {
                    return new IconView(elem);
                }
            }

            return new LabelView(elem);
        }
    }

    public static class CustomTabParagraphView extends ParagraphView {

        public CustomTabParagraphView(Element elem) {
            super(elem);
        }

        public float nextTabStop(float x, int tabOffset) {
            TabSet tabs = getTabSet();
            if (tabs == null) {
                // a tab every 72 pixels.
                return (float) (getTabBase() + (((int) x / TAB_SIZE + 1) * TAB_SIZE));
            }

            return super.nextTabStop(x, tabOffset);
        }

    }
}
