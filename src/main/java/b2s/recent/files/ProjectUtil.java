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

import org.netbeans.api.project.Project;
import org.netbeans.api.project.ui.OpenProjects;
import org.openide.filesystems.FileObject;

public class ProjectUtil {
    public String projectNameFor(FileObject fileObject) {
        Project projects[] = OpenProjects.getDefault().getOpenProjects();
        for (Project project : projects) {
            String projectPath = project.getProjectDirectory().getPath();
            if (fileObject.getPath().contains(projectPath)) {
                return project.getProjectDirectory().getName();
            }
        }
        return "";
    }
}
