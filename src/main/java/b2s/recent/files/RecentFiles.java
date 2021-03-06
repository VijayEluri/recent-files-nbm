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

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import org.openide.loaders.DataObject;

public class RecentFiles {

    private DataObjectUtil dataObjectUtil = new DataObjectUtil();
    private ProjectUtil projectUtil = new ProjectUtil();
    private LinkedList<TimeStampedDataObject> files = new LinkedList<TimeStampedDataObject>();
    private final int maxRecentFiles;

    public RecentFiles(int maxRecentFiles) {
        this.maxRecentFiles = maxRecentFiles;
    }

    public synchronized void moveToTop(DataObject dataObject) {
        remove(dataObject);
        files.addFirst(new TimeStampedDataObject(dataObject));
        if (files.size() > maxRecentFiles) {
            files.removeLast();
        }
    }

    public synchronized List<TimeStampedDataObject> asList() {
        for (int i=files.size()-1;i>=0;i--) {
            DataObject file = files.get(i).getDataObject();
            if (!dataObjectUtil.isValid(file) || projectUtil.containingProjectClosed(file)) {
                remove(file);
            }
        }
        return Collections.unmodifiableList(files);
    }

    public synchronized void remove(DataObject dataObject) {
        TimeStampedDataObject foundItem = null;
        for (TimeStampedDataObject item : files) {
            if (item.getDataObject() == dataObject) {
                foundItem = item;
            }
        }
        if (foundItem != null) {
            files.remove(foundItem);
        }
    }

    public synchronized boolean hasFiles() {
        return !files.isEmpty();
    }

    public synchronized int filesWithTheSameName(String name) {
        int count = 0;
        for (TimeStampedDataObject recentItem : files) {
            if (name.equalsIgnoreCase(recentItem.getDataObject().getName())) {
                count++;
            }
        }
        return count;
    }

    void setDataObjectUtil(DataObjectUtil dataObjectUtil) {
        this.dataObjectUtil = dataObjectUtil;
    }

    void setProjectUtil(ProjectUtil projectUtil) {
        this.projectUtil = projectUtil;
    }

}
