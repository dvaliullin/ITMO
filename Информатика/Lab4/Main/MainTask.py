def deserialize(yaml):
    lines = yaml.strip().split('\n')
    result = {}
    current = result
    parents = []
    
    for line in lines:
        if not line.strip() or line.strip()[0]=='#':
            continue  
        indent = len(line) - len(line.lstrip())
        key_value = line.strip()
        
        
        while parents and indent <= parents[-1][0]:
            current = parents.pop()[1]
        
        if key_value.endswith(':'):
            key = key_value[:-1]
            current[key] = {}
            parents.append((indent, current))
            current = current[key]
            
        elif ': ' in key_value:
            key, value = key_value.split(': ', 1)
            
            if value.startswith('[') and value.endswith(']'):
                items = value[1:-1].split(',')
                value = [item.strip() for item in items]
            current[key] = value
            
    return result

with open('расписание.yaml', 'r', encoding='utf-8') as f:
    yaml = f.read()

result = deserialize(yaml)
print(result)


with open('MainTaskResult.txt', 'w', encoding='utf-8') as f:
    f.write(str(result))
