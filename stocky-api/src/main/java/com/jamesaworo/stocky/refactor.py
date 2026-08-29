import os
import glob
import re

base_dir = r"c:\Users\kartik\Desktop\stocky\stocky\stocky-api\src\main\java\com\jamesaworo\stocky"
company_dir = os.path.join(base_dir, "features", "company")

def ensure_dir(file_path):
    os.makedirs(os.path.dirname(file_path), exist_ok=True)

def update_content(content, new_pkg, class_name, is_dao=False):
    content = re.sub(r"package\s+.*?;", f"package {new_pkg};", content, count=1)
    
    # Imports
    content = re.sub(r"import\s+com\.jamesaworo\.stocky\.features\.company\.domain\.entity\.(.*?);", 
                     r"import com.jamesaworo.stocky.entity.company.\1;", content)
    content = re.sub(r"import\s+com\.jamesaworo\.stocky\.features\.company\.domain\.enums\.(.*?);", 
                     r"import com.jamesaworo.stocky.entity.company.enums.\1;", content)
    content = re.sub(r"import\s+com\.jamesaworo\.stocky\.features\.company\.data\.repository\.(.*?);", 
                     r"import com.jamesaworo.stocky.dao.company.\1;", content)
    content = re.sub(r"import\s+com\.jamesaworo\.stocky\.features\.company\.data\.request\.(.*?);", 
                     r"import com.jamesaworo.stocky.dto.request.company.\1;", content)
    content = re.sub(r"import\s+com\.jamesaworo\.stocky\.features\.authentication\.domain\.entity\.(.*?);", 
                     r"import com.jamesaworo.stocky.entity.auth.\1;", content)
    content = re.sub(r"import\s+com\.jamesaworo\.stocky\.core\.base\.BaseModel;", 
                     r"import com.jamesaworo.stocky.entity.BaseModel;", content)
                     
    if is_dao:
        content = re.sub(r"(public\s+interface\s+\w+)Repository", r"\1Dao", content)
        content = re.sub(r"Repository", "Dao", content)
    
    return content

# 1. Entities
for f in glob.glob(os.path.join(company_dir, "domain", "entity", "*.java")):
    with open(f, 'r', encoding='utf-8') as file:
        content = file.read()
    filename = os.path.basename(f)
    new_path = os.path.join(base_dir, "entity", "company", filename)
    content = update_content(content, "com.jamesaworo.stocky.entity.company", filename.replace('.java', ''))
    ensure_dir(new_path)
    with open(new_path, 'w', encoding='utf-8') as file:
        file.write(content)

# 2. Enums
for f in glob.glob(os.path.join(company_dir, "domain", "enums", "*.java")):
    with open(f, 'r', encoding='utf-8') as file:
        content = file.read()
    filename = os.path.basename(f)
    new_path = os.path.join(base_dir, "entity", "company", "enums", filename)
    content = update_content(content, "com.jamesaworo.stocky.entity.company.enums", filename.replace('.java', ''))
    ensure_dir(new_path)
    with open(new_path, 'w', encoding='utf-8') as file:
        file.write(content)

# 3. DAOs
for f in glob.glob(os.path.join(company_dir, "data", "repository", "*.java")):
    with open(f, 'r', encoding='utf-8') as file:
        content = file.read()
    filename = os.path.basename(f).replace('Repository', 'Dao')
    new_path = os.path.join(base_dir, "dao", "company", filename)
    content = update_content(content, "com.jamesaworo.stocky.dao.company", filename.replace('.java', ''), True)
    ensure_dir(new_path)
    with open(new_path, 'w', encoding='utf-8') as file:
        file.write(content)

# 4. Specifications
for f in glob.glob(os.path.join(company_dir, "data", "request", "specification", "*.java")):
    with open(f, 'r', encoding='utf-8') as file:
        content = file.read()
    filename = os.path.basename(f)
    new_path = os.path.join(base_dir, "dao", "company", "specification", filename)
    content = update_content(content, "com.jamesaworo.stocky.dao.company.specification", filename.replace('.java', ''))
    ensure_dir(new_path)
    with open(new_path, 'w', encoding='utf-8') as file:
        file.write(content)

# 5. DTOs
for f in glob.glob(os.path.join(company_dir, "data", "request", "*.java")):
    if os.path.isfile(f):
        with open(f, 'r', encoding='utf-8') as file:
            content = file.read()
        filename = os.path.basename(f).replace('Request', 'RequestDto')
        new_path = os.path.join(base_dir, "dto", "request", "company", filename)
        content = update_content(content, "com.jamesaworo.stocky.dto.request.company", filename.replace('.java', ''))
        content = re.sub(r"(public\s+class\s+\w+)Request", r"\1RequestDto", content)
        ensure_dir(new_path)
        with open(new_path, 'w', encoding='utf-8') as file:
            file.write(content)

# 6. Controllers
for f in glob.glob(os.path.join(company_dir, "endpoint", "*.java")):
    with open(f, 'r', encoding='utf-8') as file:
        content = file.read()
    filename = os.path.basename(f).replace('Endpoint', 'Controller')
    new_path = os.path.join(base_dir, "controller", "company", filename)
    content = update_content(content, "com.jamesaworo.stocky.controller.company", filename.replace('.java', ''))
    content = re.sub(r"(public\s+class\s+\w+)Endpoint", r"\1Controller", content)
    content = re.sub(r"import\s+com\.jamesaworo\.stocky\.features\.company\.data\.interactor\.contract\.I(.*?)Interactor;", 
                     r"import com.jamesaworo.stocky.service.company.\1Service;", content)
    content = re.sub(r"private\s+final\s+I(.*?)Interactor\s+(\w+);", r"private final \1Service \2;", content)
    content = re.sub(r"I(.*?)Interactor", r"\1Service", content)
    content = re.sub(r"Request", r"RequestDto", content) # DTO name change
    ensure_dir(new_path)
    with open(new_path, 'w', encoding='utf-8') as file:
        file.write(content)

# Since mapping services and merging is very complex for a script, I will copy interactors to Service and ServiceImpl for now
for f in glob.glob(os.path.join(company_dir, "data", "interactor", "contract", "*.java")):
    with open(f, 'r', encoding='utf-8') as file:
        content = file.read()
    filename = os.path.basename(f).replace('I', '', 1).replace('Interactor', 'Service')
    new_path = os.path.join(base_dir, "service", "company", filename)
    content = update_content(content, "com.jamesaworo.stocky.service.company", filename.replace('.java', ''))
    content = re.sub(r"public\s+interface\s+I(.*?)Interactor", r"public interface \1Service", content)
    content = re.sub(r"Request", r"RequestDto", content)
    ensure_dir(new_path)
    with open(new_path, 'w', encoding='utf-8') as file:
        file.write(content)

for f in glob.glob(os.path.join(company_dir, "data", "interactor", "implementation", "*.java")):
    with open(f, 'r', encoding='utf-8') as file:
        content = file.read()
    filename = os.path.basename(f).replace('InteractorImpl', 'ServiceImpl').replace('Interactor', 'ServiceImpl')
    new_path = os.path.join(base_dir, "serviceImpl", "company", filename)
    content = update_content(content, "com.jamesaworo.stocky.serviceImpl.company", filename.replace('.java', ''))
    content = re.sub(r"public\s+class\s+(.*?)InteractorImpl", r"public class \1ServiceImpl", content)
    content = re.sub(r"public\s+class\s+(.*?)Interactor", r"public class \1ServiceImpl", content)
    content = re.sub(r"implements\s+I(.*?)Interactor", r"implements \1Service", content)
    content = re.sub(r"import\s+com\.jamesaworo\.stocky\.features\.company\.data\.interactor\.contract\.I(.*?)Interactor;", 
                     r"import com.jamesaworo.stocky.service.company.\1Service;", content)
    content = re.sub(r"@Interactor", "@Service", content)
    content = re.sub(r"import\s+com\.jamesaworo\.stocky\.core\.annotations\.Interactor;", r"import org.springframework.stereotype.Service;", content)
    content = re.sub(r"Request", r"RequestDto", content)
    ensure_dir(new_path)
    with open(new_path, 'w', encoding='utf-8') as file:
        file.write(content)

print("Migration script completed.")
