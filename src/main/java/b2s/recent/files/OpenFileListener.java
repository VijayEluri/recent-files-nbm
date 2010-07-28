/**
 * Copyright to the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the License.
 */

package b2s.recent.files;

import java.awt.Dialog;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JList;
import javax.swing.ListModel;
import org.openide.loaders.DataObject;

public class OpenFileListener extends KeyAdapter {
    private FileOpener fileOpener = new FileOpener();
    private final Dialog dialog;

    public OpenFileListener(Dialog dialog) {
        this.dialog = dialog;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JList list = (JList) e.getComponent();
        ListModel model = list.getModel();

        switch(e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                fileOpener.open((DataObject) model.getElementAt(list.getSelectedIndex()));
                dialog.dispose();
                break;
        }
    }

}
